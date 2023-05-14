package com.example.smartclosetclient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartclosetclient.dto.UserDto;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Role;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.UserApi;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity  extends AppCompatActivity {
    private EditText inputEditEmail;
    private EditText inputEditPassword;
    private Button btnSignIn;
    private TextView signUpTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        inputEditEmail = findViewById(R.id.inputEmail);
        inputEditPassword = findViewById(R.id.inputPassword);
        btnSignIn = findViewById(R.id.signInBtn);
        signUpTxt = findViewById(R.id.signUpRedirectText);
        signIn();
    }
    private void signIn(){
        signUpTxt.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);


        btnSignIn.setOnClickListener(view -> {
            String email = String.valueOf(inputEditEmail.getText());
            String password = String.valueOf(inputEditPassword.getText());

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

           UserDto user = new UserDto();
           user.setEmail(email);
           user.setPassword(password);
          //  User loginUser = new User();
            userApi.getUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user1 = response.body();
                    List<Role> roleList = new ArrayList<Role>();
                    Collection<Role> roleCollection = user1.getRoles();
                    for(Role i: roleCollection ){
                        roleList.add(i);
                    }
                    SharedPrefManager.getInstance(getApplicationContext()).setUser(user1.getClosetId(),user1.getUsername(),
                                                                                user1.getEmail(),roleList.get(0).getName());
                    //Toast.makeText(SignInActivity.this, roleList.get(0).getName(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignInActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignInActivity.this,"Sign in failed!",Toast.LENGTH_SHORT).show();
                Logger.getLogger(GetStartedActivity.class.getName()).log(Level.SEVERE, "Error occurred",t);
            }
        });


    });
}
}
