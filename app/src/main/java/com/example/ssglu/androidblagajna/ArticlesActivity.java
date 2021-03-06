package com.example.ssglu.androidblagajna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity {


    EditText etSearch;
    String searchInput;
    String order1,order2;
    ListView listView;
    ArrayList<ArticleClass> list ;
    ArrayList<ArticleClass> basket;
    ListAdapter adapter;
    Button btnCheckout;
    Toast toastMessage;
    ImageButton imgCheckout;

    boolean disableSorting = false;
    boolean nameAscDesc = true,priceAscDesc= true,quantAscDesc= true,catAscDesc= true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        getSupportActionBar().hide(); //hide action bar

        imgCheckout = (ImageButton) findViewById(R.id.imgBtnCheckout);
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<ArticleClass>();
        basket = new ArrayList<ArticleClass>();
        etSearch = (EditText)findViewById(R.id.etSearchBar);
        btnCheckout = (Button)findViewById(R.id.btnCheckout);




        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesActivity.this,CheckoutActivity.class);
                intent.putExtra("basket",basket);
                ArticlesActivity.this.startActivity(intent);
                finish();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(!ItemExistsInBasket((ArticleClass) adapter.getItem(position))){
                ArticleClass temp = new ArticleClass();
                temp.id = ((ArticleClass) adapter.getItem(position)).id;
                temp.name = ((ArticleClass) adapter.getItem(position)).name ;
                temp.price = ((ArticleClass) adapter.getItem(position)).price;
                temp.quantity = ((ArticleClass) adapter.getItem(position)).quantity;
                temp.category = ((ArticleClass) adapter.getItem(position)).category;
                temp.isVisible = ((ArticleClass) adapter.getItem(position)).isVisible;
//                    basket.add((ArticleClass)adapter.getItem(position));
                basket.add(temp);
                basket.get(basket.size()-1).quantity=1;



            }
            if (toastMessage!= null) {
                toastMessage.cancel();
            }

            toastMessage = Toast.makeText(getApplicationContext(), "Added "+((ArticleClass) adapter.getItem(position)).name,
                    Toast.LENGTH_SHORT);
            toastMessage.show();

//            if(basket.size()>0) imgCheckout.setVisibility(View.VISIBLE);
            if(basket.size()>0) btnCheckout.setVisibility(View.VISIBLE);

        }
    });



        GetJSONFromDB();


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FilterListOfArticles(s.toString());
                if(s.toString().isEmpty()) {
                    nameAscDesc = true;
                    disableSorting = false;
                    SortListOfArticles("name");

                }
                else {
                    nameAscDesc = true;
                    disableSorting = true;
                    SortListOfArticles("name");
                    SortArticlesByVisibility();
                }

                ShowDataToListView();
            }
            @Override
            public void afterTextChanged(Editable s) {            }
        });



    }
    boolean ItemExistsInBasket(ArticleClass temp){
        for(int i = 0 ; i < basket.size();i++){
            if(temp.id == basket.get(i).id){
                basket.get(i).quantity ++;
                return true;
            }
        }
        return false;
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
        RequestQueue queue = Volley.newRequestQueue(ArticlesActivity.this);
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

        adapter = new ArticleListAdapter(ArticlesActivity.this,list);

        listView.setAdapter(adapter);



    }
    void SortArticlesByVisibility(){
        Collections.sort(list, new Comparator<ArticleClass>() {
            @Override
            public int compare(ArticleClass o1, ArticleClass o2) {
                return Boolean.compare(o2.isVisible,o1.isVisible);
            }
        });

    }




    void OnClickOrderBy(View v){
        Button b = (Button)v;
        if(!disableSorting)
            SortListOfArticles(b.getText().toString());
        ShowDataToListView();

    }
    void SortListOfArticles(String parametar){

        switch (parametar){
            case "name":
                if (nameAscDesc) {
                    SortArticlesByVisibility();
                    nameAscDesc = false;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return o1.name.compareTo(o2.name);
                        }
                    });
                }
                else {
                    SortArticlesByVisibility();
                    nameAscDesc=true;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return o2.name.compareTo(o1.name);
                        }
                    });

                }

                break;
            case "price":

//                Collections.sort(list, new Comparator<ArticleClass>() {
//                    @Override
//                    public int compare(ArticleClass o1, ArticleClass o2) {
//                        return o1.price.compareTo(o2.price);
//                    }
//                });
//                break;
                if (priceAscDesc) {
                    SortArticlesByVisibility();
                    priceAscDesc = false;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return o1.price.compareTo(o2.price);
                        }
                    });
                }
                else {
                    SortArticlesByVisibility();
                    priceAscDesc=true;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return o2.price.compareTo(o1.price);
                        }
                    });

                }
                break;

            case "quantity":


//                Collections.sort(list, new Comparator<ArticleClass>() {
//                    @Override
//                    public int compare(ArticleClass o1, ArticleClass o2) {
//                        return Integer.toString(o1.quantity).compareTo(Integer.toString(o2.quantity));
//                    }
//                });
                if (quantAscDesc) {
                    SortArticlesByVisibility();
                    quantAscDesc = false;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return  Integer.compare(o1.quantity,o2.quantity);
//                            return Integer.toString(o1.quantity).compareTo(Integer.toString(o2.quantity));
                        }
                    });
                }
                else {
                    SortArticlesByVisibility();
                    quantAscDesc=true;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return Integer.compare(o2.quantity,o1.quantity);
//                            return Integer.toString(o2.quantity).compareTo(Integer.toString(o1.quantity));
                        }
                    });

                }



                break;
            case "category":
//                Collections.sort(list, new Comparator<ArticleClass>() {
//                    @Override
//                    public int compare(ArticleClass o1, ArticleClass o2) {
//                        return o1.category.compareTo(o2.category);
//                    }
//                });
                if (catAscDesc) {
                    SortArticlesByVisibility();
                    catAscDesc = false;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return o1.category.compareTo(o2.category);
                        }
                    });
                }
                else {
                    SortArticlesByVisibility();
                    catAscDesc=true;
                    Collections.sort(list, new Comparator<ArticleClass>() {
                        @Override
                        public int compare(ArticleClass o1, ArticleClass o2) {
                            return o2.category.compareTo(o1.category);
                        }
                    });

                }

                break;
        }


    }
    void FilterListOfArticles(String value){

        ArticleClass temp=new ArticleClass();
        for(int i = 0; i<list.size();i++){
            temp = list.get(i);
            if(!temp.name.toLowerCase().contains(value.toLowerCase()))
                list.get(i).isVisible = false;
           else
               list.get(i).isVisible=true;
        }

    }
}
