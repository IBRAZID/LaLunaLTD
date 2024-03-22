package com.example.lalunaltd.Utils;

import android.content.Context;
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
import com.example.lalunaltd.MainActivity;
import com.example.lalunaltd.R;
import com.example.lalunaltd.product.DetailsFragment;
import com.example.lalunaltd.Classes.Product;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    MainActivity mainAct;
    ArrayList<ItemInOrder> CartProductList;
    private FirebaseServices fbs;
    private CartAdapter.OnItemClickListener itemClickListener;


    public CartAdapter(Context context, ArrayList<ItemInOrder> ProductList) {
        this.context = context;
        this.CartProductList = ProductList;
        this.fbs = FirebaseServices.getInstance();
        this.mainAct = (MainActivity)context;

        this.itemClickListener = new CartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                 /*
                String selectedItem = filteredList.get(position).getNameCar();
                Toast.makeText(getActivity(), "Clicked: " + selectedItem, Toast.LENGTH_SHORT).show(); */
                Bundle args = new Bundle();
                args.putParcelable("Product", CartProductList.get(position).getProd()); // or use Parcelable for better performance
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
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.cartlayout,parent,false);
        return  new CartAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        Product prod = CartProductList.get(position).getProd();
        holder.tvName.setText(prod.getName());
        holder.tvPrice.setText(String.valueOf("Price:"+prod.getPrice())+" ₪");
        holder.tvQuantity.setText("Quantity:"+CartProductList.get(position).getQuantity());
        //holder.tvDescription.setText(prod.getDescription());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CartProductList.get(position).getQuantity()==1)
                    CartProductList.remove(CartProductList.get(position));
                else  CartProductList.get(position).setQuantity(CartProductList.get(position).getQuantity()-1);
                Toast.makeText(context, "Removed!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // // create for loop go to all cart array and check if prod is there

                mainAct.getCartArray().add(prod);
                Toast.makeText(context, "Added!", Toast.LENGTH_SHORT).show();
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

//        holder.tvDescription.setOnClickListener(v -> {
//            if (itemClickListener != null) {
//                itemClickListener.onItemClick(position);
//            }
//        });

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
        return CartProductList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvPrice;
        TextView tvQuantity;
        ImageView ivProduct;
        ImageButton btnAdd;
        ImageButton btnRemove;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct=itemView.findViewById(R.id.ivProductPhotoCartLayoutXml);
            tvName=itemView.findViewById(R.id.tvNameCartLayoutXml);
            tvPrice=itemView.findViewById(R.id.tvPriceCartLayoutXml);
            tvQuantity=itemView.findViewById(R.id.tvQuantityCartLayoutXml);
            btnAdd=itemView.findViewById(R.id.btnAddCartLayoutXml);
            btnRemove=itemView.findViewById(R.id.btnRemoveCartLayoutXml);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(CartAdapter.OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}

