package com.hanaahany.finalprojectorange.ui.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hanaahany.finalprojectorange.R;

import com.hanaahany.finalprojectorange.databinding.FragmentSignUpBinding;
import com.hanaahany.finalprojectorange.databinding.FragmentWelcomeTwoScreenBinding;
import com.hanaahany.finalprojectorange.ui.activity.HomeActivity;


public class SignUpFragment extends Fragment {
FragmentSignUpBinding binding;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpFragment";
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentSignUpBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        onClicks();
    }

    private void onClicks() {
        binding.tvLoginSignFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_signUpFragment_to_loginFragment);

            }
        });
        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.etEmailSignUpFragment.getText().toString().trim();
                String password=binding.etPasswordSignUpFragment.getText().toString().trim();
                String nickName=binding.etAnwapSignUpFragment.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    SignUpFragmentDirections.ActionSignUpFragmentToBioFragment action=
                                            SignUpFragmentDirections.actionSignUpFragmentToBioFragment(email,nickName);
                                    navController.navigate(action);
                                    //startActivity(new Intent(getContext(), HomeActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getContext(), task.getException().getLocalizedMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });


            }

        });


    }

    private void updateUI(FirebaseUser user) {



    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser!=null){
//            updateUI(currentUser);
//        }
//
//    }
}