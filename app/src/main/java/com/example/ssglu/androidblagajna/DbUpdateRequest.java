package com.example.ssglu.androidblagajna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class DbUpdateRequest extends StringRequest {

    private static final String UPDATE_ARTICLES_URL = "https://lrosic.000webhostapp.com/updateArticles.php";
    private static final String DELETE_ROW_URL = "https://lrosic.000webhostapp.com/deleteRow.php";
    private static final String INSERT_ROW_URL = "https://lrosic.000webhostapp.com/insertRow.php";
    private static final String EDIT_ROW_URL = "https://lrosic.000webhostapp.com/updateRow.php";

    private Map<String,String> params;
    public DbUpdateRequest(int id,
                           int quantity,
                           Response.Listener<String> listener) {
        super(Method.POST, UPDATE_ARTICLES_URL, listener, null);

        params = new HashMap<>();
        params.put("id",id+"");
        params.put("quantity",quantity+"");
    }

    public DbUpdateRequest(int id,
                           Response.Listener<String> listener) {
        super(Method.POST, DELETE_ROW_URL, listener, null);

        params = new HashMap<>();
        params.put("id",id+"");
    }
    public DbUpdateRequest(String name,
                           String price,
                           String quantity,
                           String category,
                           Response.Listener<String> listener) {
        super(Method.POST, INSERT_ROW_URL, listener, null);

        params = new HashMap<>();
        params.put("name",name);
        params.put("price",price);
        params.put("quantity",quantity);
        params.put("category",category);


    }
    public DbUpdateRequest(int id,
                           String name,
                           String price,
                           String quantity,
                           String category,
                           Response.Listener<String> listener) {
        super(Method.POST, EDIT_ROW_URL, listener, null);

        params = new HashMap<>();
        params.put("id",id+"");
        params.put("name",name);
        params.put("price",price);
        params.put("quantity",quantity);
        params.put("category",category);


    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}