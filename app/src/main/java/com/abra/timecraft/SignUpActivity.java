package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abra.timecraft.data.UserConfig;
import com.abra.timecraft.models.UserModel;

public class SignUpActivity extends AppCompatActivity {


    EditText  etUser, etEmail, etPassword;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setup();
    }

    private void setup() {

        etUser = findViewById(R.id.et_reusuario);
        etEmail = findViewById(R.id.et_recorreo);
        etPassword = findViewById(R.id.et_recontrasena);

        Button btnSignUp = findViewById(R.id.btn_registrar);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        Button btncuentaya = findViewById(R.id.btn_cuentaexistente);
        btncuentaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void signUp() {
        if(!validateFields()) {
            return;
        }
        userModel = new UserModel(etUser.getText().toString(), etEmail.getText().toString(),etPassword.getText().toString());
        saveUser(userModel);
        navigateToMain(userModel);
    }

    private void saveUser(UserModel user) {
        UserConfig userConfig = new UserConfig(getApplicationContext());
        userConfig.setUser(user);
    }

    private void navigateToMain(UserModel user) {
        Intent intent = new Intent(this, LoginActivity.class);

        //la proxima activity ahora será la primera en el back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(MainActivity.USER_KEY, user.getUser());
        intent.putExtra(MainActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(MainActivity.PASSWORD_KEY, user.getPassword());
        startActivity(intent);
    }

    private boolean validateFields() {
        if(etUser.getText() == null || TextUtils.isEmpty(etUser.getText().toString())) {
            etUser.setError(getString(R.string.user_error));
            return false;
        }
        if(etEmail.getText() == null || TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(getString(R.string.password_error));
            return false;
        }
        if(etEmail.getText() == null || TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(getString(R.string.email_error));
            return false;
        }

        showMessage("Sus datos han sido registrado con exito!");
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