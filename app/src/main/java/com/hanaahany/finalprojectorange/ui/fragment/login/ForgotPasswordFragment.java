package com.hanaahany.finalprojectorange.ui.fragment.login;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentForgotPasswordBinding;


public class ForgotPasswordFragment extends Fragment {

    FragmentForgotPasswordBinding binding;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentForgotPasswordBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        onClicks();



    }




    private void onClicks() {
        binding.cardEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 LoginFragmentArgs args = LoginFragmentArgs.fromBundle(getArguments());
                String email=args.getEmail();
                navController.navigate(R.id.action_forgotPasswordFragment_to_resetPasswordFragment);
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getContext(), "Email Sent", Toast.LENGTH_SHORT).show();
                                }
                            }


                        });


            }
        });
    }
}