package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

        btnFinish = (Button)findViewById(R.id.btnFinish);
        basket = new ArrayList<ArticleClass>();
        listView = (ListView)findViewById(R.id.listView) ;


        primljeniIntent= getIntent();
        basket = (ArrayList<ArticleClass>)primljeniIntent.getSerializableExtra("basket");


        adapter = new BasketListAdapter(CheckoutActivity.this,basket);
        listView.setAdapter(adapter);




        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseLsitener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);
                                builder.setMessage("Update Success")
                                                        .setNegativeButton("Retry",null)
                                                        .create()
                                                        .show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);
                                builder.setMessage("Update failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(CheckoutActivity.this);

                for (int i = 0 ;i<basket.size();i++) {
                    DbUpdateRequest dbUpdateREQ = new DbUpdateRequest(basket.get(i).id,basket.get(i).quantity,responseLsitener);
                    queue.add(dbUpdateREQ);
                }

                finish();


            }
        });








    }
}
