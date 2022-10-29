package com.hanaahany.finalprojectorange.ui.fragment.main.home.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentExploreCategoryProductBinding;
import com.hanaahany.finalprojectorange.model.RetrofitAPI;
import com.hanaahany.finalprojectorange.model.RetrofitClient;
import com.hanaahany.finalprojectorange.ui.fragment.main.home.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreCategoryProductFragment extends Fragment {

    ArrayList<List<String>> arrayList=new ArrayList<>();
    CategoryAdapter categoryAdapter;
    RetrofitAPI retrofitAPI;
    FragmentExploreCategoryProductBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentExploreCategoryProductBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         retrofitAPI=new RetrofitClient().getInstance().create(RetrofitAPI.class);
        getAllCategory();
    }

    private void getAllCategory() {
        retrofitAPI.getAllCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                for (int i=0;i<response.body().size();i++){
                    arrayList.add(response.body());
                   categoryAdapter=new CategoryAdapter(arrayList);
                }
                binding.recyclerAllCategories.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }
}