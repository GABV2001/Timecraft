package com.abra.timecraft.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abra.timecraft.BuildConfig;
import com.abra.timecraft.models.UserModel;

import java.util.Locale;

public class UserConfig {


    private static final String USER_PREF_NAME = "user_prefs";
    private static final String PREF_FIRST_TIME = "is_first_time";
    private static final String PREF_USER_NAME = "user_name";
    private static final String PREF_USER_EMAIL = "user_email";
    private static final String PREF_PASSWORD_EMAIL = "user_password";




    private final SharedPreferences mPrefs;

    public UserConfig(@NonNull Context context) {
        mPrefs = context.getSharedPreferences(getPrefsName(), Context.MODE_PRIVATE);
    }

    public boolean isFirstTime() {
        return mPrefs.getBoolean(PREF_FIRST_TIME, true);
    }

    public boolean setIsFirstTime(Boolean value) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(PREF_FIRST_TIME, value);
        prefsEditor.apply();
        return true;
    }

    public boolean userExists() {
        return mPrefs.contains(PREF_USER_NAME) && mPrefs.contains(PREF_PASSWORD_EMAIL) && mPrefs.contains(PREF_USER_EMAIL);
    }


    @Nullable
    public UserModel getUser() {

        final String usern = mPrefs.getString(PREF_USER_NAME, null);
        final String email = mPrefs.getString(PREF_USER_EMAIL, null);
        final String pass = mPrefs.getString(PREF_PASSWORD_EMAIL, null);
        if(TextUtils.isEmpty(usern) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            return null;
        }
        return new UserModel(usern, email, pass);
    }

    public boolean setUser(@Nullable UserModel user) {
        if(user == null) return false;
        if(TextUtils.isEmpty(user.getUser()) || TextUtils.isEmpty(user.getEmail()) || TextUtils.isEmpty(user.getPassword()))
            return false;

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(PREF_USER_NAME, user.getUser());
        prefsEditor.putString(PREF_USER_EMAIL, user.getEmail());
        prefsEditor.putString(PREF_PASSWORD_EMAIL, user.getPassword());
        prefsEditor.apply();
        return true;
    }

    public boolean readuser(UserModel model){

        String emailvalue = model.getEmail();
        String passwordvalue = model.getPassword();

        System.out.println("email: " +emailvalue);
        System.out.println("password: " + passwordvalue);

        final String email = mPrefs.getString(PREF_USER_EMAIL, "");
        final String password = mPrefs.getString(PREF_PASSWORD_EMAIL, "");

        return !emailvalue.equals(email) || !passwordvalue.equals(password);

    }


    private String getPrefsName() {
        return String.format(
                Locale.getDefault(),
                "%s_%s",
                BuildConfig.APPLICATION_ID , USER_PREF_NAME
        );
    }

}
