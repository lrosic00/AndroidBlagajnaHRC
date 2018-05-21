package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    EditText etUsername,etPassword;
    Button btnLogin;
    UserInfoClass userInfo = new UserInfoClass();
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide(); //hide action bar




        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btnLogin = (Button)findViewById(R.id.btnLogin);
//        tvRegister = (TextView)findViewById(R.id.tvRegister);

//        tvRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(registerIntent);
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

//                                String fullName = jsonResponse.getString("fullName");
//////                                int age = jsonResponse.getInt("Age");
//////                                int id_user = jsonResponse.getInt("id_user");
//////
//////                                boolean isAdmin;
                                userInfo.id_user = jsonResponse.getInt("id_user");
                                userInfo.username = username;
                                userInfo.fullName = jsonResponse.getString("fullName");
                                userInfo.password = password;
                                userInfo.age = jsonResponse.getInt("Age");

                                if(jsonResponse.getInt("IsAdmin")==1)
                                    userInfo.isAdmin = true;
                                else
                                    userInfo.isAdmin = false;



                                Intent intent = new Intent(LoginActivity.this,IndexActivity.class);

//                                intent.putExtra("id_user",id_user);
//                                intent.putExtra("username",username);
//                                intent.putExtra("fullName",fullName);
//                                intent.putExtra("password",password);
//                                intent.putExtra("age",age);
//                                intent.putExtra("IsAdmin",isAdmin);
                                intent.putExtra("userInfo",userInfo);


                                LoginActivity.this.startActivity(intent);


                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginREQ = new LoginRequest(username,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginREQ);
            }
        });


    }
}
