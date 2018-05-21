package com.example.ssglu.androidblagajna;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.basket_product_list,parent,false);

        if(!getItem(position).isVisible) return customView;
        TextView tvName = (TextView)customView.findViewById(R.id.tvName);
        TextView tvPrice = (TextView)customView.findViewById(R.id.tvPrice);
        EditText tvQuant = (EditText) customView.findViewById(R.id.etQuant);
        TextView tvCat = (TextView)customView.findViewById(R.id.tvCat);
        ImageButton btnDel = (ImageButton)customView.findViewById(R.id.imgDel);


        tvName.setText(getItem(position).name);
        tvPrice.setText(String.valueOf(getItem(position).price)+ " kn");
        tvQuant.setText(String.valueOf(getItem(position).quantity));
        tvCat.setText(getItem(position).category);








        return customView;
    }
}
