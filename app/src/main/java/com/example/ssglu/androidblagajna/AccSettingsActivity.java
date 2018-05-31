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

public class AccSettingsActivity extends AppCompatActivity {

    UserInfoClass userInfo = new UserInfoClass();
    EditText etUsername,etFullName,etPassword,etAge;
    Button btnSaveChanges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_settings);
        getSupportActionBar().hide(); //hide action bar

        Intent intent = getIntent();
        userInfo = (UserInfoClass)intent.getSerializableExtra("userInfo");

        btnSaveChanges = (Button)findViewById(R.id.btnSaveChanges);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etFullName = (EditText)findViewById(R.id.etFullName);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etAge = (EditText)findViewById(R.id.etAge);


        etUsername.setText(userInfo.username);
        etFullName.setText(userInfo.fullName);
        etPassword.setText(userInfo.password);
        etAge.setText(Integer.toString(userInfo.age));

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!etUsername.getText().toString().isEmpty() &&
                        !etFullName.getText().toString().isEmpty() &&
                        !etPassword.getText().toString().isEmpty() &&
                        etAge.getText().toString().isEmpty()) {

                    final String tempUsername,tempFullname,tempPass,tempAge;

                    tempUsername = etUsername.getText().toString();
                    tempFullname = etFullName.getText().toString();
                    tempPass = etPassword.getText().toString();
                    tempAge = etAge.getText().toString();


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsonResponse = null;
                            try {
                                jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if(success){
                                    userInfo.username=tempUsername;
                                    userInfo.fullName=tempFullname;
                                    userInfo.password=tempPass;
                                    userInfo.age=Integer.parseInt(tempAge);
                                    Intent intent = new Intent(AccSettingsActivity.this,IndexActivity.class);
                                    intent.putExtra("userInfo",userInfo);

                                    AccSettingsActivity.this.startActivity(intent);

                                }else{

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    UpdateAccSettingsRequest accSetReq = new UpdateAccSettingsRequest(Integer.toString(userInfo.id_user),tempUsername,tempFullname,tempPass,tempAge,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(AccSettingsActivity.this);
                    queue.add(accSetReq);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccSettingsActivity.this);
                    builder.setMessage("Please fill all fields")
                            .setNegativeButton("Retry",null)
                            .create()
                            .show();
                }


            }
        });

    }
}
