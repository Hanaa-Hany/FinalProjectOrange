package com.hanaahany.finalprojectorange.ui.fragment.login;

import static android.app.Activity.RESULT_OK;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentBioBinding;
import com.hanaahany.finalprojectorange.databinding.FragmentUploadPhotoBinding;
import com.hanaahany.finalprojectorange.ui.activity.HomeActivity;


public class UploadPhotoFragment extends Fragment {

    FragmentUploadPhotoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUploadPhotoBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imagePickPhotoUploadFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        binding.btnNextUploadFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_uploadPhotoFragment_to_locationFragment);
               // startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&& resultCode==RESULT_OK&& data!=null){
            Uri imageUri=data.getData();
            SharedPreferences sharedPreferences= getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("Image", String.valueOf(imageUri)).apply();

        }
    }
}