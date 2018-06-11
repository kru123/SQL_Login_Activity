package com.example.user.login_activity;


import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    Context context;

    PrefManager(Context context) {
        this.context = context;
    }

    // Method to save logindetails which we will call in Mainactivity.

    public boolean saveLoginDetails(String username, String college, String emailid) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username);
        editor.putString("College", college);
        editor.putString("Email", emailid);
        editor.apply();
        getUsername();
        getCollege();
        getEmailid();
        isUserLogedOut();
        return true;

    }
//Under all are the methods to return username,email,collegename.

    public String getUsername() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Username", "");
    }

    public String getCollege() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("College", "");
    }

    public String getEmailid() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }


    //Method to check whether the user is logedout or not.

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isUsernameEmpty = sharedPreferences.getString("Username", "").isEmpty();
        boolean isCollegeEmpty = sharedPreferences.getString("College", "").isEmpty();
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        return isEmailEmpty || isCollegeEmpty || isUsernameEmpty;
    }

}