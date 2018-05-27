package com.example.ssglu.androidblagajna;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ModifiyDatabaseAdapter extends ArrayAdapter<ArticleClass> {

    public ModifiyDatabaseAdapter(@NonNull Context context,  List<ArticleClass> myProductList) {
        super(context, R.layout.database_product_list,myProductList);
    }

    @Nullable
    @Override
    public ArticleClass getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.database_product_list,parent,false);

        if(!getItem(position).isVisible){
            customView.setVisibility(View.GONE);
            customView.destroyDrawingCache();


            return customView;
        }
        TextView tvName = (TextView)customView.findViewById(R.id.tvName);
        TextView tvPrice = (TextView)customView.findViewById(R.id.tvPrice);
        TextView tvQuant = (TextView)customView.findViewById(R.id.tvQuant);
        TextView tvCat = (TextView)customView.findViewById(R.id.tvCat);
        Button btnEdit = (Button)customView.findViewById(R.id.btnEdit);


        tvName.setText(getItem(position).name);
        tvPrice.setText(String.valueOf(getItem(position).price)+ " kn");
        tvQuant.setText(String.valueOf(getItem(position).quantity));
        tvCat.setText(getItem(position).category);


        return customView;
    }
}
