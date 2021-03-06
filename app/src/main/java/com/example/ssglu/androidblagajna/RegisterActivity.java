package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername,etFullName,etPassword,etAge;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide(); //hide action bar

        etUsername = (EditText)findViewById(R.id.etUsername);
        etFullName = (EditText)findViewById(R.id.etFullName);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etAge = (EditText)findViewById(R.id.etAge);

        btnRegister = (Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etUsername.getText().toString().isEmpty() &&
                        !etFullName.getText().toString().isEmpty() &&
                        !etPassword.getText().toString().isEmpty() &&
                        !etAge.getText().toString().isEmpty()) {


                    final String username = etUsername.getText().toString();
                    final String fullName = etFullName.getText().toString();
                    final String password = etPassword.getText().toString();
                    final int age  = Integer.parseInt(etAge.getText().toString());



                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if(success){
                                    Intent intent = new Intent(RegisterActivity.this,IndexActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Register failed")
                                            .setNegativeButton("Retry",null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegisterRequest registerREQ = new RegisterRequest(username,fullName,password,age,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerREQ);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Please fill all fields")
                            .setNegativeButton("Retry",null)
                            .create()
                            .show();
                }


            }
        });


    }
}
