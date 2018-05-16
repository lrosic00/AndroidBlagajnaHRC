package com.example.ssglu.androidblagajna;


import java.io.Serializable;

public class ArticleClass implements Serializable {

    int id;
    String name;
    Double price;
    int quantity;
    String category;
    boolean isVisible = true;

}
