package com.example.lalunaltd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> ProductList;
    private FirebaseServices fbs;

    public ProductAdapter(Context context, ArrayList<Product> ProductList) {
        this.context = context;
        this.ProductList = ProductList;
        this.fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.productlayout,parent,false);
        return  new ProductAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Product prod = ProductList.get(position);
        holder.tvName.setText(prod.getName());
        holder.tvDescription.setText(prod.getDescription());
        if (prod.getImage() == null || prod.getImage().isEmpty())
        {
            Picasso.get().load(R.drawable.baseline_add_24).into(holder.ivProduct);
        }
        else {
            Picasso.get().load(prod.getImage()).into(holder.ivProduct);
        }

    }

    @Override
    public int getItemCount(){
        return ProductList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView ivProduct;
        TextView tvDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct=itemView.findViewById(R.id.ivProductPhotoProductLayout);
            tvName=itemView.findViewById(R.id.tvNameLayoutXml);
            tvDescription=itemView.findViewById(R.id.tvDescriptionLayoutXml);
        }
    }
}

