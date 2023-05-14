package com.example.smartclosetclient.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.smartclosetclient.GetStartedActivity;
import com.example.smartclosetclient.model.User;
import com.google.gson.Gson;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "retrofitregisterlogin";
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_EMAIL = "key_email";

    private static final String KEY_ROLE = "key_role";
    private static final String KEY_CLOSETID = "key_closetId";

    private  static   SharedPrefManager mInstance;
    private static Context ctx;

    //Constructor
    public SharedPrefManager(Context context) {
        ctx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public static void setUser(Long closetId, String userName, String email,String roleName){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_CLOSETID, closetId);
        editor.putString(KEY_USERNAME, userName);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROLE, roleName);
        editor.apply();
    }

    public Long getClosetId() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(KEY_CLOSETID,-1);
    }
    public String getUsername() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null);
    }
    public String getEmail() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }
    public String getRole() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLE,null);
    }


    //This method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //This method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, GetStartedActivity.class));
    }


}
