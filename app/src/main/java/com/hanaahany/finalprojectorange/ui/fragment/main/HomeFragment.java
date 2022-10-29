package com.hanaahany.finalprojectorange.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentHomeBinding;
import com.hanaahany.finalprojectorange.model.ProductModel;
import com.hanaahany.finalprojectorange.model.RetrofitAPI;
import com.hanaahany.finalprojectorange.model.RetrofitClient;
import com.hanaahany.finalprojectorange.ui.activity.CategoryActivity;
import com.hanaahany.finalprojectorange.ui.activity.ProductActivity;
import com.hanaahany.finalprojectorange.ui.fragment.main.home.CategoryAdapter;
import com.hanaahany.finalprojectorange.ui.fragment.main.home.product.ProductAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    FragmentHomeBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<List<String>>arrayListCategory=new ArrayList<>();
    ProductAdapter productAdapter;
    ArrayList<ProductModel> arrayListProduct=new ArrayList<>();
    NavController navControlle;
    private static final String TAG = "HomeFragment";
    RetrofitAPI retrofitAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //navControlle= Navigation.findNavController(view);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        binding.recyclerCategories.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager1=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);

        binding.recyclerProducts.setLayoutManager(layoutManager1);

        retrofitAPI= new RetrofitClient().getInstance().create(RetrofitAPI.class);
        getCategory();
        getLimitProduct();
        viewMoreProduct();
        viewMoreCategory();


    }

    private void viewMoreCategory() {
       binding.tvViewMoreCategory.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getContext(), CategoryActivity.class));
           }
       });
    }


    private void viewMoreProduct() {
        binding.tvViewMoreProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ProductActivity.class));
                //navControlle.navigate(R.id.action_homeFragment_to_exploreProductFragment);

            }
        });
    }

    private void getLimitProduct() {
        retrofitAPI.getLimitProducts(3).enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                for (int i = 0; i <response.body().getProducts().size() ; i++) {
                    arrayListProduct.add(response.body());
                    productAdapter=new ProductAdapter(arrayListProduct);
                    Log.i(TAG, "onResponse: "+response.body());
                }
                binding.recyclerProducts.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void getCategory() {
        retrofitAPI.getAllCategories().enqueue(new Callback <List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response <List<String>> response) {

                for (int i = 0; i <response.body().size() ; i++) {
                    Log.i(TAG, "onResponse: "+response.body());

                    arrayListCategory.add(response.body());
                    categoryAdapter=new CategoryAdapter(arrayListCategory);
                }
                binding.recyclerCategories.setAdapter(categoryAdapter);

            }

            @Override
            public void onFailure(Call <List<String>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });
    }


}