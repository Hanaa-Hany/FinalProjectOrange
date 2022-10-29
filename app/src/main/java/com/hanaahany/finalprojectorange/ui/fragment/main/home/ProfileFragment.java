package com.hanaahany.finalprojectorange.ui.fragment.main.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

FragmentProfileBinding binding;
    private static final String TAG = "ProfileFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String email=sharedPreferences.getString("Email","");
        String userName=sharedPreferences.getString("UserName","");
        Uri image= Uri.parse(sharedPreferences.getString("Image",""));
        Log.i(TAG, "onViewCreated: "+email);
        binding.tvNameProfile.setText(userName);
        binding.tvEmailProfile.setText(email);
        binding.imageProfile.setImageURI(image);
    }
}