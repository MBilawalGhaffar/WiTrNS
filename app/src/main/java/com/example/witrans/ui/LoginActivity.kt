package com.example.witrans.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.witrans.MainActivity
import com.example.witrans.R
import com.example.witrans.databinding.ActivityLoginBinding
import com.example.witrans.dialog.WaitDialog
import com.example.witrans.model.Driver
import com.example.witrans.model.UserAuth
import com.example.witrans.preference.AuthPreference
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.mindrot.jbcrypt.BCrypt

class LoginActivity : AppCompatActivity() {
    val LoginTag="Login_Tag"

    private lateinit var binding: ActivityLoginBinding
    private lateinit var etEmail:TextInputEditText
    private lateinit var etPassword:TextInputEditText
    private var mEmail:String?=null
    private var mPassword:String?=null
    private var waitDialog: WaitDialog?=null
    private lateinit var authPreference: AuthPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etEmail=binding.etEmail
        etPassword=binding.etPassword
        waitDialog= WaitDialog(this)
        authPreference= AuthPreference(this)



//        checkBcrpt("123")

        binding.buttonLogin.setOnClickListener {

            Log.d(LoginTag,"button login call")
            mEmail=etEmail.text.toString()
            if(mEmail.isNullOrEmpty()){
                etEmail.error ="Field can't be empty"
                return@setOnClickListener
            }
            mPassword=etPassword.text.toString()
            if(mPassword.isNullOrEmpty()){
                etPassword.error="Field can't be empty"
                return@setOnClickListener
            }
            waitDialog!!.show()
            getDriverDetails(mEmail,mPassword)
        }

    }

    private fun checkBcrpt(s: String) {
        Log.d("BCrypt","******************************")
        Log.d("BCrypt","password : $s")
        val hash=BCrypt.hashpw(s,BCrypt.gensalt())
        Log.d("BCrypt","hashpw : $hash")

        val isVslid=BCrypt.checkpw(s,hash)

        val hash2=BCrypt.hashpw(s,BCrypt.gensalt())
        val isVslid2=BCrypt.checkpw("124",hash)
        Log.d("BCrypt","isVslid : $isVslid")

        Log.d("BCrypt","isVslid2 : $isVslid2")


    }

    private fun onSuccess() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun getDriverDetails(mEmail: String?, mPassword: String?) {
        Log.d(LoginTag,"getDriverDetails call")
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()
            val mediaType = "application/json".toMediaType()
//
//            val driverBody="{" +
//                    "\"collection\":\"driver\"," +
//                    "\"database\":\"WiTrans\","+
//                    "\"dataSource\":\"WiTrans\","+
//                    "\"filter\":{\"username\":\"test12\"}"+
//                    "}"

            val driverBody="{" +
                    "\"collection\":\"driver\"," +
                    "\"database\":\"WiTrans\","+
                    "\"dataSource\":\"WiTrans\","+
                    "\"filter\":{\"username\":\"$mEmail\"}"+
                    "}"


            Log.d(LoginTag,"request body ->$driverBody")

            val request = Request.Builder()
//            .url("https://data.mongodb-api.com/app/data-oageq/endpoint/data/beta/action/findOne")
                .url("https://data.mongodb-api.com/app/data-yskei/endpoint/data/v1/action/findOne")
                .header("Content-Type", "application/json")
                .header("Access-Control-Request-Headers", "*")
                .header("api-key", getString(R.string.apiKey))
                .post(driverBody.toRequestBody(mediaType))
                .build()

            runCatching {
                val response = client.newCall(request).execute()
                Log.d(LoginTag,"runCatching ->$response")
                val body = response.body!!.string()
                val objJson = JSONObject(body)
                Log.d(LoginTag,"body ->$body")
                Log.d(LoginTag,"objJson ->$objJson")
                val mDriverObject=objJson.getJSONObject("document")
                Log.d(LoginTag,"objJson ->$mDriverObject")
                try {
                    val _id=mDriverObject.getString("_id")
                    val username=mDriverObject.getString("username")
                    val driverId=mDriverObject.getString("driverId")
                    val dob=mDriverObject.getString("dob")
                    val email=mDriverObject.getString("email")
                    val phoneNumber=mDriverObject.getString("phoneNumber")
                    val address=mDriverObject.getString("address")
                    val name=mDriverObject.getString("name")
                    val password=mDriverObject.getString("password")

                    val mDriver=Driver(_id, username, driverId, dob, email, phoneNumber, address, name, password)
                    Log.d(LoginTag,"mDriver ->$mDriver")
                    checkCredentials(mPassword,mDriver)
                }catch (e:Exception){
                    Log.d(LoginTag,"exception ->${e.message}")
                    waitDialog!!.dismiss()
                }


//                val array =objJson.getJSONArray("documents")
//                for (i in 0 until array.length()) {
//                    val itemObj = array.getJSONObject(i)
//
//                }
                waitDialog!!.dismiss()

            }
        }



    }

    private fun checkCredentials(mPassword: String?, mDriver: Driver) {
        val isValidPassword=isValid(mPassword!!,mDriver.password)
        if(isValidPassword){
            saveData(mDriver)
            Log.d(LoginTag,"isValidPassword ->${isValidPassword}")
        }else{
            waitDialog!!.dismiss()
            Toast.makeText(this,"invalid password, Please enter correct password",Toast.LENGTH_SHORT).show()
        }

    }


    private fun saveData(mDriver: Driver) {
        authPreference.addAuth(UserAuth(true,mDriver.email,mDriver.username,mDriver.password))
        waitDialog!!.dismiss()
        Log.d(LoginTag,"saveData -> onSuccess()")
    onSuccess()
    }

    private fun isValid(clearTextPassword: String, hashedPass: String): Boolean {
        // returns true if password matches hash
        return BCrypt.checkpw(clearTextPassword, hashedPass)
    }

}