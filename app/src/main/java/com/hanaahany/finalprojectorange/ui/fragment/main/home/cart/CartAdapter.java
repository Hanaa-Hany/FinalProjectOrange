package com.hanaahany.finalprojectorange.ui.fragment.main.home.cart;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.model.ProductModel;
import com.hanaahany.finalprojectorange.model.ProductModelDetails;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartVH> {

    private static final String TAG = "CartAdapter";
    ArrayList<ProductModelDetails>arrayList;

    public CartAdapter(ArrayList<ProductModelDetails> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new CartVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        CartFragment cartFragment=new CartFragment();
        CartFragmentArgs cartFragmentArgs=CartFragmentArgs.fromBundle(cartFragment.getArguments());
        String name=cartFragmentArgs.getProduct().getTitle();
        String category=cartFragmentArgs.getProduct().getCategory();
        String description=cartFragmentArgs.getProduct().getDescription();
        double price= cartFragmentArgs.getProduct().getPrice();
        holder.textViewCategory.setText(category);
        holder.textViewPrice.setText(price+"");
        holder.textViewName.setText(name);


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class CartVH extends RecyclerView.ViewHolder {
        TextView textViewName,textViewPrice,textViewCategory;
        public CartVH(@NonNull View itemView) {
            super(itemView);
             textViewName=itemView.findViewById(R.id.tv_name_product_cart_item);
             textViewPrice=itemView.findViewById(R.id.tv_price_product_cart_item);
             textViewCategory=itemView.findViewById(R.id.tv_category_product_cart_item);
        }
    }
}
