package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {

    TextView tvName,tvNameData,tvAge,tvAgeData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide(); //hide action bar

        tvName = (TextView)findViewById(R.id.tvName);
        tvNameData = (TextView)findViewById(R.id.tvNameData);
        tvAge = (TextView)findViewById(R.id.tvAge);
        tvAgeData = (TextView)findViewById(R.id.tvAgeData);

        Intent intent = getIntent();
        int age = intent.getIntExtra("age",-1);


        tvNameData.setText(intent.getStringExtra("fullName"));
        tvAgeData.setText(age+"");

    }
}
