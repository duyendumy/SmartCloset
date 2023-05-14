package com.example.smartclosetclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartclosetclient.dto.UserDto;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.UserApi;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private  TextView txtSignIn;
    private EditText inputEditName;
    private EditText inputEditEmail;
    private EditText inputEditPassword;
    private EditText inputEditConfirmPassword;
    private Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtSignIn = findViewById(R.id.loginRedirectText);
        inputEditName= findViewById(R.id.signup_name);
        inputEditEmail = findViewById(R.id.signup_email);
        inputEditPassword = findViewById(R.id.signup_password);
        inputEditConfirmPassword = findViewById(R.id.signup_confirm_password);
        btnSignUp = findViewById(R.id.signup_button);

        signUp();
        signIn();
    }

    private void signIn(){
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
            });

    }
    private void signUp(){
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        btnSignUp.setOnClickListener(view -> {
            String name = String.valueOf(inputEditName.getText());
            String email = String.valueOf(inputEditEmail.getText());
            String password = String.valueOf(inputEditPassword.getText());
            String confirmPassword = String.valueOf(inputEditConfirmPassword.getText());

            if(name.isEmpty()){
                inputEditName.requestFocus();
                inputEditName.setError("Please enter your name");
                return;
            }
            if(email.isEmpty()){
                inputEditEmail.requestFocus();
                inputEditEmail.setError("Please enter your email");
                return;
            }
            if(password.isEmpty()){
                inputEditPassword.requestFocus();
                inputEditPassword.setError("Please enter your password");
                return;
            }
            if(confirmPassword.isEmpty()){
                inputEditConfirmPassword.requestFocus();
                inputEditConfirmPassword.setError("Please enter your confirm password");
                return;
            }
            if(!confirmPassword.equals(password)){
                inputEditConfirmPassword.requestFocus();
                inputEditConfirmPassword.setError("Password did not match");
                return;
            }

            UserDto user = new UserDto();
            user.setUsername(name);
            user.setEmail(email);
            user.setPassword(password);

            userApi.save(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(SignUpActivity.this,"Save successful!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this,"Save failed!",Toast.LENGTH_SHORT).show();
                    Logger.getLogger(GetStartedActivity.class.getName()).log(Level.SEVERE, "Error occurred",t);
                }
            });


        });
    }
}
