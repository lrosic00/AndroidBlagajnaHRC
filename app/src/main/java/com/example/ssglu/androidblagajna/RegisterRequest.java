package com.example.ssglu.androidblagajna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://lrosic.000webhostapp.com/register.php";


    private Map<String,String> params;
    public RegisterRequest(String username,
                           String fullName,
                           String password,
                           int age,
                           Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("username",username);
        params.put("fullName",fullName);
        params.put("password",password);
        params.put("Age",age + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
