package com.hanaahany.finalprojectorange.ui.fragment.login;

import android.content.Context;
import android.content.SharedPreferences;
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


public class BioFragment extends Fragment {
FragmentBioBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentBioBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!=null){
            BioFragmentArgs args=BioFragmentArgs.fromBundle(getArguments());
            binding.etEmailBioFragment.setText(args.getEmail());
            binding.etUserNameBioFragment.setText(args.getUserName());
        }

        onClick();



    }

    private void onClick() {
        binding.btnNextBioFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController=Navigation.findNavController(view);
                navController.navigate(R.id.action_bioFragment_to_uploadPhotoFragment);
                saveDataInSharedPrefrences();

            }
        });
    }

    private void saveDataInSharedPrefrences() {
        String nickName=binding.etUserNameBioFragment.getText().toString();
        String email=binding.etEmailBioFragment.getText().toString();
        String phone=binding.etMobileBioFragment.getText().toString();

        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("UserName",nickName).apply();
        sharedPreferences.edit().putString("Email",email).apply();
        sharedPreferences.edit().putString("Phone",phone).apply();
    }
}