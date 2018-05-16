package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    Intent primljeniIntent;
    ArrayList<ArticleClass> basket;
    Button btnFinish,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        basket = new ArrayList<ArticleClass>();

        primljeniIntent= getIntent();
        basket = (ArrayList<ArticleClass>)primljeniIntent.getSerializableExtra("basket");





    }
}
