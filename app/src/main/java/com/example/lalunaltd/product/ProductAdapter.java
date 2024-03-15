package com.example.lalunaltd.product;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalunaltd.Classes.ItemInOrder;
import com.example.lalunaltd.Classes.Order;
import com.example.lalunaltd.Classes.Product;
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
    private ProductAdapter.OnItemClickListener itemClickListener;


    public ProductAdapter(Context context, ArrayList<Product> ProductList) {
        this.context = context;
        this.ProductList = ProductList;
        this.fbs = FirebaseServices.getInstance();
        this.mainAct = (MainActivity)context;

        this.itemClickListener = new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*
                String selectedItem = filteredList.get(position).getNameCar();
                Toast.makeText(getActivity(), "Clicked: " + selectedItem, Toast.LENGTH_SHORT).show(); */
                Bundle args = new Bundle();
                args.putParcelable("car", ProductList.get(position)); // or use Parcelable for better performance
                DetailsFragment cd = new DetailsFragment();
                cd.setArguments(args);
                FragmentTransaction ft= ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain,cd);
                ft.commit();
            }
        };
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.productlayout,parent,false);
        return  new ProductAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {

        ArrayList<Product> CartArr=mainAct.getCartArray();
        Product prod = ProductList.get(position);
        holder.tvName.setText(prod.getName());
        holder.tvPrice.setText(String.valueOf(prod.getPrice())+" ₪");
        holder.tvDescription.setText(prod.getDescription());
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean doesExist=false;
                /* TODO: if exists: check if product is already in orderitem arraylist
                    for(ItemInOrder i : order.GetItemInOrdersList())
                    {
                       if (prod.getId().equals(i.getProductId()))
                       {
                          i.setQuant....
                       }
                       else
                       {
                          .....
                       }
                    }

                *
                * */


                //ItemInOrder item =new ItemInOrder(prod.getProductId());
                //mainAct.getCartArray().add(prod);
                for (Product i:CartArr ){
                   Product p= CartArr.get(position) ;
                   if (p.getProductId().equals(prod));
                    {
                        doesExist=true;
                        break;
                    }
                }
                if (doesExist==true)
                {
                    item.setQuantity(item.getQuantity()+1);
                }
                else {

                }

                Toast.makeText(context, "Successfully Added Item To Cart!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvName.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });

        holder.tvPrice.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });

        holder.tvDescription.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });

        holder.ivProduct.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}

