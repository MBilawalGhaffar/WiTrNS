package com.example.witrans

import android.content.Context

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.witrans.adapter.PagerAdapter
import com.example.witrans.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    var TAG = "mongodb"
    var API = "api_hit"

    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.elevation= 0F
        initUI()
        setUpViewPager()


//        Realm.init(this)
//        val app = App(AppConfiguration.Builder(getString(R.string.mongodb_app_id)).build())
//        Log.v(TAG,"app id ->${app.configuration.appId}")
//        Log.v(TAG,"app name ->${app.configuration.appName}")


//        val anonymousCredentials: Credentials = Credentials.anonymous()
//        Log.d(TAG,"anonymousCredentials -> ${anonymousCredentials.asJson()}")

//        val emailPasswordCredentials: Credentials = Credentials.emailPassword(
//            "info@wi-trans.de",
//            "MIANdji@1122."
//        )

//        var user: User?
//
//
//        app.loginAsync(emailPasswordCredentials) {
//            if (it.isSuccess) {
//                Log.v(TAG, "Successfully authenticated.")
//                user = app.currentUser()
//                Log.v(TAG, "user ->${user.toString()}")
//            } else {
//                Log.e(TAG, it.error.toString())
//            }
//        }
//
//        val config = SyncConfiguration.Builder(app.currentUser(), "PARTITION")
//            .allowQueriesOnUiThread(true)
//            .allowWritesOnUiThread(true)
//            .build()
//        Realm.getInstanceAsync(config, object : Realm.Callback() {
//            override fun onSuccess(realm: Realm) {
//
//                Log.v(TAG, "Successfully opened a realm with reads and writes allowed on the UI thread.")
//            }
//
//            override fun onError(exception: Throwable) {
//                super.onError(exception)
//                Log.e(TAG, exception.message.toString())
//            }
//        })

//        val connectionString =
//            ConnectionString("mongodb+srv://<username>:<password>@witrans.fwewt30.mongodb.net/?retryWrites=true&w=majority")
//        val settings: MongoClientSettings = MongoClientSettings.builder()
//            .applyConnectionString(connectionString)
//            .serverApi(
//                ServerApi.builder()
//                    .version(ServerApiVersion.V1)
//                    .build()
//            )
//            .build()
//        val mongoClient: MongoClient = MongoClients.create(settings)
//        val database = mongoClient.getDatabase("test")



        getResponse(this)
    }

    private fun setUpViewPager() {
        pagerAdapter= PagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0 -> {tab.text=getString(R.string.current_tours)}
                1 -> {tab.text=getString(R.string.all_tours)}
                2 -> {tab.text=getString(R.string.driver_license)}
            }
        }.attach()
    }

    private fun initUI() {
        tabLayout=binding.tabLayout
        viewPager=binding.viewPager
    }

    private fun getResponse(context: Context) {
        Log.d(API,"**************************************************************************")
        Log.d(API,"call getResponse")
        CoroutineScope(Dispatchers.IO).launch {
            val client =OkHttpClient()
            val mediaType = "application/json".toMediaType()
            val bodyReq = "{\n      \"collection\":\"customer\",\n    \"database\":\"WiTrans\",\n    \"dataSource\":\"WiTrans\",\n    \"projection\": {\"_id\": 1}\n}"
            val mBody="{" +
                    "\"collection\":\"orders\"," +
                    "\"database\":\"WiTrans\","+
                    "\"dataSource\":\"WiTrans\","+
                    "\"filter\":{\"username\":\"123\"}"+
                    "}"
            val driverBody="{" +
                    "\"collection\":\"driver\"," +
                    "\"database\":\"WiTrans\","+
                    "\"dataSource\":\"WiTrans\","+
                    "\"filter\":{\"username\":\"test12\"}"+
                    "}"
            val driverOrders="{" +
                    "\"collection\":\"orders\"," +
                    "\"database\":\"WiTrans\","+
                    "\"dataSource\":\"WiTrans\","+
                    "\"filter\":{\"driver\":\"test12\"}"+
                    "}"

            val request = Request.Builder()
//            .url("https://data.mongodb-api.com/app/data-oageq/endpoint/data/beta/action/findOne")
                .url("https://data.mongodb-api.com/app/data-yskei/endpoint/data/v1/action/find")
                .header("Content-Type", "application/json")
                .header("Access-Control-Request-Headers", "*")
                .header("api-key", getString(R.string.apiKey))
                .post(driverOrders.toRequestBody(mediaType))
                .build()

//            Log.d(API,"bodyReq -> ${bodyReq}")
//            Log.d(API,"mBody -> ${mBody}")
//            Log.d(API,"mediaType -> ${mediaType}")
//            Log.d(API,"request -> ${request}")


            runCatching {
                val response = client.newCall(request).execute()
                val body = response.body!!.string()


                Log.d(API,"**************************************************************************")
                Log.d(API,"response -> ${body}")
                val objJson = JSONObject(body)
                val array =objJson.getJSONArray("documents")
                for (i in 0 until array.length()) {
                    val itemObj = array.getJSONObject(i)
                    Log.d(API,"**************************************************************************")
                    Log.d(API,"item$i -> ${itemObj}")
                }

//                CoroutineScope(Dispatchers.Main).launch {
//
//                }
            }
        }


    }
}