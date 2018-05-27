package com.example.ssglu.androidblagajna;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class ModifyDatabaseRequest extends StringRequest {
    private static final String ARTICLE_REQUEST_URL = "https://lrosic.000webhostapp.com/article.php";


    private Map<String,String> params;
    public ModifyDatabaseRequest(Response.Listener<String> listener) {

        super(Request.Method.POST, ARTICLE_REQUEST_URL, listener, null);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
