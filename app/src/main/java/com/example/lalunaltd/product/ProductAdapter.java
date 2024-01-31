package com.example.lalunaltd.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalunaltd.MainActivity;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    MainActivity mainAct;
    ArrayList<Product> ProductList;
    private FirebaseServices fbs;

    public ProductAdapter(Context context, ArrayList<Product> ProductList) {
        this.context = context;
        this.ProductList = ProductList;
        this.fbs = FirebaseServices.getInstance();
        this.mainAct = (MainActivity)context;
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
        holder.tvPrice.setText(String.valueOf(prod.getPrice()));
        holder.tvDescription.setText(prod.getDescription());
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAct.getCartArray().add(prod);
                Toast.makeText(context, "Successfully Added Item To Cart!", Toast.LENGTH_SHORT).show();
            }
        });
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
        TextView tvPrice;
        ImageView ivProduct;
        ImageButton btnAddToCart;
        TextView tvDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct=itemView.findViewById(R.id.ivProductPhotoProductLayoutXml);
            tvName=itemView.findViewById(R.id.tvNameLayoutXml);
            tvPrice=itemView.findViewById(R.id.tvPriceLayoutXml);
            tvDescription=itemView.findViewById(R.id.tvDescriptionLayoutXml);
            btnAddToCart=itemView.findViewById(R.id.btnAddToCartProductLayoutXml);
        }
    }
}

