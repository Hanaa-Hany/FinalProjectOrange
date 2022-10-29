package com.hanaahany.finalprojectorange.ui.fragment.main.home.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductVH> {
ArrayList<ProductModel> arrayListProduct;

    public ProductAdapter(ArrayList<ProductModel> arrayListProduct) {
        this.arrayListProduct = arrayListProduct;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ProductVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {
        holder.textViewTitle.setText(arrayListProduct.get(position).getProducts().get(position).getTitle());
        holder.textViewCategory.setText(arrayListProduct.get(position).getProducts().get(position).getCategory());
        holder.textViewPrice.setText(arrayListProduct.get(position).getProducts().get(position).getPrice()+"");
        Picasso.get().load(arrayListProduct.get(position).getProducts().get(position).getImages().get(0)).into(holder.imageViewProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController= Navigation.findNavController(view);
                navController.navigate(ExploreProductFragmentDirections.actionExploreProductFragmentToProductDetailsFragment()
                        .setId(arrayListProduct.get(holder.getAdapterPosition()).getProducts().get(holder.getAdapterPosition()).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListProduct.size();
    }

    class ProductVH extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewPrice,textViewTitle,textViewCategory;

        public ProductVH(@NonNull View itemView) {
            super(itemView);
            imageViewProduct=itemView.findViewById(R.id.image_product_item);
            textViewPrice=itemView.findViewById(R.id.tv_price_product_item);
            textViewCategory=itemView.findViewById(R.id.tv_category_product_item);
            textViewTitle=itemView.findViewById(R.id.tv_name_product_item);
        }
    }



}
