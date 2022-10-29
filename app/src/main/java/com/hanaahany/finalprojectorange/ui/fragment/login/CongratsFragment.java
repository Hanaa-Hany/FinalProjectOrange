package com.hanaahany.finalprojectorange.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentCartBinding;
import com.hanaahany.finalprojectorange.databinding.FragmentCongratsBinding;
import com.hanaahany.finalprojectorange.ui.activity.HomeActivity;


public class CongratsFragment extends Fragment {
FragmentCongratsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentCongratsBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnNextCongratsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),HomeActivity.class));
            }
        });
    }
}