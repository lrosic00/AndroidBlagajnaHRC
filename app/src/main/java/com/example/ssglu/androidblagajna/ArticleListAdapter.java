package com.example.ssglu.androidblagajna;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAdapter extends ArrayAdapter<ArticleClass>{

//    private Context myContext;
//    private List<ArticleClass> myProductList;

    public ArticleListAdapter(@NonNull Context context, List<ArticleClass> myProductList) {
        super(context,R.layout.item_product_list, myProductList);

    }

    @Nullable
    @Override
    public  ArticleClass getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.item_product_list,parent,false);

        if(!getItem(position).isVisible) return customView;
        TextView tvName = (TextView)customView.findViewById(R.id.tvName);
        TextView tvPrice = (TextView)customView.findViewById(R.id.tvPrice);
        TextView tvQuant = (TextView)customView.findViewById(R.id.tvQuantity);
        TextView tvCat = (TextView)customView.findViewById(R.id.tvCategory);


        tvName.setText(getItem(position).name);
        tvPrice.setText(String.valueOf(getItem(position).price)+ " kn");
        tvQuant.setText(String.valueOf(getItem(position).quantity));
        tvCat.setText(getItem(position).category);


     //NIJE POTREBNO
     //U ARTICLES ACTIVITY RJESENO!
//        customView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getContext(), getItem(position).name, Toast.LENGTH_SHORT).show();
//
//            }
//        });


        return customView;

    }


//    @Nullable
//    @Override
//    public ArticleClass getItem(int position) {
//        Toast.makeText(getContext() , getItem(position).name+getItem(position).category, Toast.LENGTH_SHORT).show();
//        return super.getItem(position);
//    }
    //    public ArticleListAdapter(Context myContext, List<ArticleClass> myProductList) {
//        this.myContext = myContext;
//        this.myProductList = myProductList;
//    }

//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return myProductList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View v = View.inflate(myContext,R.layout.item_product_list,null);
//        TextView tvName = (TextView)v.findViewById(R.id.tvName);
//        TextView tvPrice = (TextView)v.findViewById(R.id.tvPrice);
//        TextView tvQuant = (TextView)v.findViewById(R.id.tvQuantity);
//        TextView tvCat = (TextView)v.findViewById(R.id.tvCategory);
//
//
//
//        tvName.setText(myProductList.get(position).name);
//        tvPrice.setText(String.valueOf(myProductList.get(position).price)+ " kn");
//        tvQuant.setText(String.valueOf(myProductList.get(position).quantity));
//        tvCat.setText(myProductList.get(position).category);
//
//
//        v.setTag(myProductList.get(position).id);
//        return v;
//    }
}
