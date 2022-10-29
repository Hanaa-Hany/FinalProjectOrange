package com.hanaahany.finalprojectorange.ui.fragment.main.home.cart;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.Toast;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentCartBinding;
import com.hanaahany.finalprojectorange.model.ProductModelDetails;
import com.hanaahany.finalprojectorange.ui.activity.HomeActivity;

import java.util.ArrayList;


public class CartFragment extends Fragment {

ArrayList<ProductModelDetails >arrayList=new ArrayList<>();
CartAdapter cartAdapter;
FragmentCartBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentCartBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onClicks();


//        Log.i(TAG, "onViewCreated: "+name);
//        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("Product", Context.MODE_PRIVATE);
//        sharedPreferences.edit().putString("Product",name).apply();
//        sharedPreferences.edit().putString("category",category).apply();

            //Log.i(TAG, "onCreateView: "+CartFragmentArgs.fromBundle(getArguments()).getProduct().toString());
//        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
//            binding.recyclerView.setLayoutManager(layoutManager);
//           // ProductModelDetails productModelDetails=new ProductModelDetails(price,name,description,category);
//           // arrayList.add(productModelDetails);
//            cartAdapter=new CartAdapter(arrayList);
//            binding.recyclerView.setAdapter(cartAdapter);
//        Log.i(TAG, "onViewCreated: "+arrayList.size());

        initViews();

    }

    private void onClicks() {

    }

    private void initViews() {
        CartFragmentArgs cartFragmentArgs=CartFragmentArgs.fromBundle(getArguments());
        String name=cartFragmentArgs.getProduct().getTitle();
        String category=cartFragmentArgs.getProduct().getCategory();
        String description=cartFragmentArgs.getProduct().getDescription();
        double price= cartFragmentArgs.getProduct().getPrice();

        binding.tvCategoryProductCartItem.setText(category);
        binding.tvNameProductCartItem.setText(name);
        binding.tvPriceProductCartItem.setText(price+"");
       // binding.imageProductItem.setImageURI(Uri.parse(cartFragmentArgs.getProduct().getImages().get(1)));


        binding.increaseIconProductDetailsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt((binding.tvAmountProductDetailsFragment.getText() + ""));
                binding.tvAmountProductDetailsFragment.setText(amount + 1 + "");
                double newPrice=(double)Math.round ((price * (amount + 1)) * 100.0) / 100.0;
                binding.tvPriceProductCartItem.setText( newPrice+ "");
                binding.tvSubTotalPriceCart.setText(newPrice+"");
                binding.tvDeliveryPriceChargedCart.setText("10");
                double total=newPrice+10;
                binding.tvTotalPriceCart.setText(total+"");
            }
        });
        binding.minusIconProductDetailsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt((binding.tvAmountProductDetailsFragment.getText() + ""));
                if (amount == 1) {
                    Toast.makeText(getContext(), "Cant decrease", Toast.LENGTH_SHORT).show();
                } else {
                    binding.tvAmountProductDetailsFragment.setText(amount - 1 + "");
                    double price1 = Double.parseDouble(binding.tvPriceProductCartItem.getText() + "");
                    double newPrice=(double)Math.round ((price1 - price ) * 100.0) / 100.0;
                    binding.tvPriceProductCartItem.setText( newPrice+ "");
                    binding.tvSubTotalPriceCart.setText(newPrice+"");
                    binding.tvDeliveryPriceChargedCart.setText("10");
                    double total=newPrice+10;
                    binding.tvTotalPriceCart.setText(total+"");
                }
            }
        });
        
        binding.btnPlaceOrderCartFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_cartFragment_to_successOrderFragment);
            }
        });
    }
}