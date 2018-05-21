package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {


    Button btnNewReciept,btnAccSettings,btnModDB,btnRegister;

//    String username,fullName,password;
//    int id_user,age;
//    boolean isAdmin;
    UserInfoClass userInfo = new UserInfoClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide(); //hide action bar

        btnNewReciept = (Button)findViewById(R.id.btnNewReceipt);
        btnAccSettings = (Button)findViewById(R.id.btnAccSettings);
        btnModDB = (Button)findViewById(R.id.btnModDB);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        Intent intent = getIntent();
//        id_user = intent.getIntExtra("id_user",-1);
//        username = intent.getStringExtra("username");
//        fullName = intent.getStringExtra("fullName");
//        password = intent.getStringExtra("password");
//        age = intent.getIntExtra("age",-1);
//        isAdmin = intent.getBooleanExtra("IsAdmin",false);
        userInfo = (UserInfoClass)intent.getSerializableExtra("userInfo");

        if(userInfo.isAdmin){
            btnModDB.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
        }

        btnNewReciept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(IndexActivity.this,ArticlesActivity.class);
                startActivity(newIntent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(IndexActivity.this,RegisterActivity.class);
                startActivity(newIntent);
            }
        });

        btnAccSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(IndexActivity.this,AccSettingsActivity.class);
                newIntent.putExtra("userInfo",userInfo);
                startActivity(newIntent);
            }
        });


    }
}
