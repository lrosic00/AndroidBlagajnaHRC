package com.example.ssglu.androidblagajna;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BasketListAdapter extends ArrayAdapter<ArticleClass>{


    public BasketListAdapter(@NonNull Context context, List<ArticleClass> myProductList)
    {
        super(context,R.layout.basket_product_list,myProductList);
    }

    @NonNull
    @Override
    public ArticleClass getItem(int position){
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        final View customView = myInflater.inflate(R.layout.basket_product_list,parent,false);

        if(!getItem(position).isVisible) return customView;
        TextView tvName = (TextView)customView.findViewById(R.id.tvName);
        TextView tvPrice = (TextView)customView.findViewById(R.id.tvPrice);
        EditText etQuant = (EditText) customView.findViewById(R.id.etQuant);
        TextView tvCat = (TextView)customView.findViewById(R.id.tvCat);
        ImageButton btnDel = (ImageButton)customView.findViewById(R.id.imgDel);


        tvName.setText(getItem(position).name);
        tvPrice.setText(String.valueOf(getItem(position).price)+ " kn");
        etQuant.setText(String.valueOf(getItem(position).quantity));
        tvCat.setText(getItem(position).category);



        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(BasketListAdapter.super.getItem(position));

            }
        });

        etQuant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getItem(position).quantity = Integer.parseInt(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });




        return customView;
    }

    @Override
    public void remove(@Nullable ArticleClass object) {
        super.remove(object);
    }
}
