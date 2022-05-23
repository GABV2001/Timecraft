package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.abra.timecraft.data.UserConfig;

public class StartActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchFirstActivity();
    }
    private void launchFirstActivity() {
        UserConfig userConfig = new UserConfig(getApplicationContext());
        Intent intent;
        if(userConfig.isFirstTime()) {
            intent = new Intent(getBaseContext(), OnboardingActivity.class);
        } else {
            if(userConfig.userExists()) {
                intent = new Intent(getBaseContext(), MainActivity.class);
            } else {
                intent = new Intent(getBaseContext(), LoginActivity.class);
            }
        }
        startActivity(intent);

        //finalizar esta actividad
        finish();
    }
}