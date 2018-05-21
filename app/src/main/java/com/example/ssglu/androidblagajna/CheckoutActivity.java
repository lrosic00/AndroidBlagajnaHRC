package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    Intent primljeniIntent;
    ArrayList<ArticleClass> basket;
    Button btnFinish,btnBack;
    ListView listView;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        basket = new ArrayList<ArticleClass>();
        listView = (ListView)findViewById(R.id.listView) ;


        primljeniIntent= getIntent();
        basket = (ArrayList<ArticleClass>)primljeniIntent.getSerializableExtra("basket");


        adapter = new BasketListAdapter(CheckoutActivity.this,basket);
        listView.setAdapter(adapter);





    }
}
