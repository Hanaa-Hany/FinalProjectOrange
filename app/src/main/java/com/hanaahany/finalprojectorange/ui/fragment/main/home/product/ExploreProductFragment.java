package com.hanaahany.finalprojectorange.ui.fragment.main.home.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.finalprojectorange.databinding.FragmentExploreProductBinding;
import com.hanaahany.finalprojectorange.model.ProductModel;
import com.hanaahany.finalprojectorange.model.RetrofitAPI;
import com.hanaahany.finalprojectorange.model.RetrofitClient;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreProductFragment extends Fragment {

   FragmentExploreProductBinding binding;
   ProductAdapter productAdapter;
   ArrayList<ProductModel> arrayListProducts=new ArrayList<>();
    private static final String TAG = "ExploreProductFragment";
    RetrofitAPI retrofitAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentExploreProductBinding.inflate(getLayoutInflater(),container,false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.recyclerAllProducts.setLayoutManager(layoutManager);
        retrofitAPI=new RetrofitClient().getInstance().create(RetrofitAPI.class);
        onClicks();
        //searchProducts();
        filterImage();
        getAllProducts();
        //filterImage();





    }

    private void filterImage() {
        binding.imageFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filterProducts();

            }
        });


    }

    private void filterProducts() {
//        retrofitAPI.getFilterProducts("desc").enqueue(new Callback<ProductModel>() {
//            @Override
//            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
//                for (int i = 0; i <5; i++){
//                    arrayListProducts.add(response.body());
//                    productAdapter=new ProductAdapter(arrayListProducts);
//                    Log.i(TAG, "onResponse: "+arrayListProducts.get(i).getProducts().get(i).getId());
//
//                }
//
//                binding.recyclerAllProducts.setAdapter(productAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<ProductModel> call, Throwable t) {
//
//                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
//            }
//        });


    }

    private void getAllProducts() {

        retrofitAPI.getAllProducts().enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                for (int i = 0; i <response.body().getProducts().size() ; i++) {
                    arrayListProducts.add(response.body());
                   productAdapter=new ProductAdapter(arrayListProducts);
                    Log.i(TAG, "onResponse: "+arrayListProducts.get(i).getProducts().get(i).getImages());
                }
                binding.recyclerAllProducts.setAdapter(productAdapter);

            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();


    }
    private void onClicks() {
        binding.searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchProducts();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchProducts();
            }
        });
    }

    private void searchProducts() {

        retrofitAPI.searchProduct(binding.searchView.getText().toString()).enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                for (int i = 0; i <response.body().getProducts().size() ; i++) {
                    arrayListProducts.add(response.body());
                    productAdapter=new ProductAdapter(arrayListProducts);
                    Log.i(TAG, "onResponse: "+arrayListProducts.get(i).getProducts().size());
                }
                binding.recyclerAllProducts.setAdapter(productAdapter);
                //binding.recyclerAllProducts.notify();

            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

            }
        });
    }


}