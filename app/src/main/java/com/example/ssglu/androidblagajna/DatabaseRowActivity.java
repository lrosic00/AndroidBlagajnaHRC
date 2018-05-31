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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DatabaseRowActivity extends AppCompatActivity {

    ArticleClass dbRow;
    TextView tvName,tvPrice,tvQuant,tvCat;
    EditText etName,etPrice,etQuant,etCat;
    Button btnFinish;
    boolean IsUserEditing= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_row);
        getSupportActionBar().hide(); //hide action bar

        btnFinish = (Button)findViewById(R.id.btnUpdateDB);

        tvName = (TextView)findViewById(R.id.tvName);
        tvPrice = (TextView)findViewById(R.id.tvPrice);
        tvQuant = (TextView)findViewById(R.id.tvQuant);
        tvCat = (TextView)findViewById(R.id.tvCategory);

        etName = (EditText)findViewById(R.id.etName);
        etPrice = (EditText)findViewById(R.id.etPrice);
        etQuant = (EditText)findViewById(R.id.etQuantity);
        etCat = (EditText)findViewById(R.id.etCategory);

        Intent intent = getIntent();
        dbRow = (ArticleClass) intent.getSerializableExtra("dbRow");

        if (dbRow !=null) {
            IsUserEditing = true;
            etName.setText(dbRow.name);
            etPrice.setText(dbRow.price.toString());
            etQuant.setText(Integer.toString(dbRow.quantity));
            etCat.setText(dbRow.category);
        }


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etName.getText().toString().isEmpty() &&
                        !etPrice.getText().toString().isEmpty() &&
                        !etQuant.getText().toString().isEmpty() &&
                        !etCat.getText().toString().isEmpty()) {
                    Response.Listener<String> responseLsitener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(DatabaseRowActivity.this);
                                    builder.setMessage("Update Success")
                                            .setNegativeButton("Continue", null)
                                            .create()
                                            .show();
                                } else {
                                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(DatabaseRowActivity.this);
                                    builder.setMessage("Update failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(DatabaseRowActivity.this);
                    DbUpdateRequest dbUpdateREQ;
                    if (IsUserEditing) {
                        dbUpdateREQ = new DbUpdateRequest(dbRow.id, etName.getText().toString(), etPrice.getText().toString(), etQuant.getText().toString(), etCat.getText().toString(), responseLsitener);
                    } else {
                        dbUpdateREQ = new DbUpdateRequest(etName.getText().toString(), etPrice.getText().toString(), etQuant.getText().toString(), etCat.getText().toString(), responseLsitener);
                    }

                    queue.add(dbUpdateREQ);
//                DatabaseRowActivity.this.finish();  //NEKAD PUKNE APP ZBOG OVOG

                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(DatabaseRowActivity.this);
                    builder.setMessage("Please fill all fields")
                            .setNegativeButton("Retry",null)
                            .create()
                            .show();
                }

            }
        });



    }
}
