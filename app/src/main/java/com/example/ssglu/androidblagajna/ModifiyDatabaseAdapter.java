package com.example.ssglu.androidblagajna;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
        ImageButton imgBtn = (ImageButton)customView.findViewById(R.id.imageButton);


        tvName.setText(getItem(position).name);
        tvPrice.setText(String.valueOf(getItem(position).price)+ " kn");
        tvQuant.setText(String.valueOf(getItem(position).quantity));
        tvCat.setText(getItem(position).category);





        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openDialog();


//                remove(getItem(position));
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(getContext(),DatabaseRowActivity.class);
                ArticleClass temp = getItem(position);
                newIntent.putExtra("dbRow",temp);
                getContext().startActivity(newIntent);

            }
        });

        return customView;
    }


    @Override
    public void remove(@Nullable ArticleClass object) {
        super.remove(object);
    }

    public void openDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Are you sure you want to delete this item from database?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {


            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
