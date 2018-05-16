package com.example.ssglu.androidblagajna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ArticleRequest extends StringRequest {


    private static final String ARTICLE_REQUEST_URL = "https://lrosic.000webhostapp.com/article.php";


    private Map<String,String> params;
    public ArticleRequest(Response.Listener<String> listener) {

        super(Method.POST, ARTICLE_REQUEST_URL, listener, null);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
