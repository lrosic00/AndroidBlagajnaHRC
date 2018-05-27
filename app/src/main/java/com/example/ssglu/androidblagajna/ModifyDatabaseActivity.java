package com.example.ssglu.androidblagajna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ModifyDatabaseActivity extends AppCompatActivity {

    List list;
    ListView listView;
    ModifiyDatabaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_database);

        listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<ArticleClass>();

        GetJSONFromDB();


    }

    void GetJSONFromDB(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success) {
                        FillArrayList(jsonResponse);
                        ShowDataToListView();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        ArticleRequest articleREQ = new ArticleRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(ModifyDatabaseActivity.this);
        queue.add(articleREQ);



    }
    void FillArrayList(JSONObject jsonResponse) throws JSONException {
        int i = 0;
        while( i<jsonResponse.getInt("counter")) {


            ArticleClass temp= new ArticleClass() ;
            try {
                temp.id=jsonResponse.getJSONObject(Integer.toString(i)).getInt("id");
                temp.name=jsonResponse.getJSONObject(Integer.toString(i)).getString("name");
                temp.price=jsonResponse.getJSONObject(Integer.toString(i)).getDouble("price");
                temp.quantity=jsonResponse.getJSONObject(Integer.toString(i)).getInt("quantity");
                temp.category=jsonResponse.getJSONObject(Integer.toString(i)).getString("category");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            list.add(temp);
            i++;

        }
    }
    void ShowDataToListView(){

        adapter = new ModifiyDatabaseAdapter(ModifyDatabaseActivity.this,list);

        listView.setAdapter(adapter);



    }
}
