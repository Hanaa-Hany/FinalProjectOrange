package com.hanaahany.finalprojectorange.ui.fragment.main.home.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentProductDetailsBinding;
import com.hanaahany.finalprojectorange.model.ProductModel;
import com.hanaahany.finalprojectorange.model.ProductModelDetails;
import com.hanaahany.finalprojectorange.model.RetrofitAPI;
import com.hanaahany.finalprojectorange.model.RetrofitClient;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsFragment extends Fragment {
    private static final String TAG = "ProductDetailsFragment";
    FragmentProductDetailsBinding binding;


    int id;
    double price;
    NavController navController;
    String title;
    String description;
    String category;
    RequestCreator image ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProductDetailsBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        id= ProductDetailsFragmentArgs.fromBundle(getArguments()).getId();
        Log.i(TAG, "onViewCreated: "+id);
        setViews();
        onClicks();
       // RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),)

    }

    private void setViews() {
        RetrofitAPI retrofitAPI=new RetrofitClient().getInstance().create(RetrofitAPI.class);
        retrofitAPI.getSingleProduct(id).enqueue(new Callback<ProductModelDetails>() {
            @Override
            public void onResponse(Call<ProductModelDetails> call, Response<ProductModelDetails> response) {
                title=response.body().getTitle();
                description=response.body().getDescription();
                price=response.body().getPrice();
                category=response.body().getCategory();
                binding.tvTitleProductDetailsFragment.setText(title);
                binding.tvDescriptionProductDetailsFragment.setText(description);
                binding.tvPriceProductDetailsFragment.setText(price+"");
                //binding.tvCountProductDetailsFragment.setText(response.body().getRatingProduct().getCount()+"");
                binding.tvRateProductDetailsFragment.setText(response.body().getRating()+"");
                image = Picasso.get().load(String.valueOf(response.body().getImages()));
                Picasso.get().load(response.body().getImages().get(0)).into(binding.imageItemProductDetailsFragment);
                binding.tvCategoryProductDeatilsFragment.setText(category);


                Log.i(TAG, "onResponse: "+response.body().getDescription());
            }

            @Override
            public void onFailure(Call<ProductModelDetails> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private void onClicks() {
        binding.tvAmountProductDetailsFragment.setText("1");
        increaseProduct();
        decreaseProduct();
        addToCart();



    }

    private void addToCart() {


        binding.btnAddToCartProductDetailsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductModelDetails productModelDetails=new ProductModelDetails(price,title,description,category);

                ProductDetailsFragmentDirections.ActionProductDetailsFragmentToCartFragment action =
                        ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartFragment(productModelDetails);
                action.setProduct(productModelDetails);
                Toast.makeText(getContext(), "Product Added", Toast.LENGTH_SHORT).show();
                navController.navigate(action);

            }
        });
    }

    private void decreaseProduct() {
        binding.minusIconProductDetailsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt((binding.tvAmountProductDetailsFragment.getText() + ""));
                if (amount == 1) {
                    Toast.makeText(getContext(), "Cant decrease", Toast.LENGTH_SHORT).show();
                } else {
                    binding.tvAmountProductDetailsFragment.setText(amount - 1 + "");
                    double price1 = Double.parseDouble(binding.tvPriceProductDetailsFragment.getText() + "");
                    double newPrice=(double)Math.round ((price1 - price ) * 100.0) / 100.0;
                    binding.tvPriceProductDetailsFragment.setText(newPrice + "");

                }
            }
        });

    }

    private void increaseProduct() {
        binding.increaseIconProductDetailsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt((binding.tvAmountProductDetailsFragment.getText() + ""));
                binding.tvAmountProductDetailsFragment.setText(amount + 1 + "");
                double newPrice=(double)Math.round ((price * (amount + 1)) * 100.0) / 100.0;
                binding.tvPriceProductDetailsFragment.setText( newPrice+ "");
            }
        });
    }

}