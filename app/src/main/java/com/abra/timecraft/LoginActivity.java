package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abra.timecraft.data.UserConfig;
import com.abra.timecraft.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    boolean confirmacion = true;
    UserModel userModel;

    private SharedPreferences mPrefs;


    private static final String PREF_USER_EMAIL = "user_email";
    private static final String PREF_PASSWORD_EMAIL = "user_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setup();
    }

    private void setup() {

        etEmail = findViewById(R.id.et_correo);
        etPassword = findViewById(R.id.et_contraseña);

        Button btnSignUp = findViewById(R.id.btnIniciarsesion);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });
    }

    public  void  onClick(View view){
        switch (view.getId()){
            case R.id.btn_pregistrar:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void LogIn() {
        if(!validateFields()) {
            return;
        }
        userModel = new UserModel(etEmail.getText().toString(),etPassword.getText().toString());
        veriUser(userModel);
    }

    private void veriUser(UserModel userModel) {

        UserConfig userConfig = new UserConfig(getApplicationContext());
        userConfig.readuser(userModel);
        confirmacion = userConfig.readuser(userModel);

         if (!confirmacion){
            navigateToMain(userModel);
        }else{
            Toast.makeText(LoginActivity.this, "Correo o Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMain(UserModel user) {
        Intent intent = new Intent(this, MainActivity.class);

        //la proxima activity ahora será la primera en el back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(MainActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(MainActivity.PASSWORD_KEY, user.getPassword());
        startActivity(intent);
    }

    private boolean validateFields() {
        if(etEmail.getText() == null || TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(getString(R.string.email_error));
            return false;
        }
        if(etPassword.getText() == null || TextUtils.isEmpty(etPassword.getText().toString())) {
            etEmail.setError(getString(R.string.password_error));
            return false;
        }

        return true;
    }

    private void showMessage(String message) {
        Toast.makeText(
                this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

}