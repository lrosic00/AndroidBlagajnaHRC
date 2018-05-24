package com.example.ssglu.androidblagajna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class DbUpdateRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://lrosic.000webhostapp.com/updateArticles.php";


    private Map<String,String> params;
    public DbUpdateRequest(int id,
                           int quantity,
                           Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("id",id+"");
        params.put("quantity",quantity+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}