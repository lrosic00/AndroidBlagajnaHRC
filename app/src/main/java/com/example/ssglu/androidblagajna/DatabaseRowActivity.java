package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DatabaseRowActivity extends AppCompatActivity {

    ArticleClass dbRow;
    TextView tvName,tvPrice,tvQuant,tvCat;
    EditText etName,etPrice,etQuant,etCat;
    Button finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_row);

        finish = (Button)findViewById(R.id.btnFinish);

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
            etName.setText(dbRow.name);
            etPrice.setText(dbRow.price.toString());
            etQuant.setText(Integer.toString(dbRow.quantity));
            etCat.setText(dbRow.category);
        }



    }
}
