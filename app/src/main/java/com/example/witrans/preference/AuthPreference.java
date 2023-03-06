package com.example.witrans.preference;



import android.content.Context;
import android.content.SharedPreferences;

import com.example.witrans.model.UserAuth;


public class AuthPreference {
    private final SharedPreferences sharedPreferences;

    public AuthPreference(Context context) {
        sharedPreferences=context.getSharedPreferences("AUTH",Context.MODE_PRIVATE);
    }

    public UserAuth getAuth() {
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        String userEmail=sharedPreferences.getString("userEmail","");
        String userId=sharedPreferences.getString("userId","");
        String userPassword=sharedPreferences.getString("userPassword","");
        return new UserAuth(isLogin,userEmail,userId,userPassword);

    }
    public void addAuth(UserAuth userAuth) {
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean("isLogin",userAuth.isLogin());
        myEdit.putString("userEmail",userAuth.getUserEmail());
        myEdit.putString("userId",userAuth.getUserId());
        myEdit.putString("userPassword",userAuth.getUserPassword());
        myEdit.apply();
    }
}
