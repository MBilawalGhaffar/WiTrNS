package com.example.witrans.model;

public class UserAuth {
    private boolean isLogin=false;
    private String userEmail ="";
    private String userId ="";
    private String userPassword ="";

    public UserAuth(boolean isLogin, String userEmail,String userId, String userPassword) {
        this.isLogin = isLogin;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public UserAuth() {
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
