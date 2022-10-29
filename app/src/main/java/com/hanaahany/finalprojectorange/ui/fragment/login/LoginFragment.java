package com.hanaahany.finalprojectorange.ui.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentLoginBinding;
import com.hanaahany.finalprojectorange.databinding.FragmentWelcomeTwoScreenBinding;


public class LoginFragment extends Fragment {
FragmentLoginBinding binding;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "LoginFragment";
    private static final int RC_SIGN_IN = 9001;
    NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();
        onClicks();
// Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("412117464930-o0rih8no4vv4klogopddtp065c3inv2p.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
//
//
//        binding.btnGoogleLoginFragment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });





    }

    private void onClicks() {
        loginButton();
        forgotTV();

    }

    private void forgotTV() {
        binding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginFragmentDirections.ActionLoginFragmentToForgotPasswordFragment actionLoginFragmentToForgotPasswordFragment=
                        LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(binding.etEmailLoginFragment.getText().toString().trim());
                navController.navigate(actionLoginFragmentToForgotPasswordFragment);
            }
        });
    }

    private void loginButton() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.etEmailLoginFragment.getText().toString().trim();
                String password=binding.etPasswordLoginFragment.getText().toString().trim();
                Log.i(TAG, "onClick: "+email);
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getContext(), task.getException().getLocalizedMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    // updateUI(null);
                                }
                            }
                        });
                //SharedPreferences sharedPreferences= getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

            }
        });
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






    private void updateUI() {
        navController.navigate(R.id.action_loginFragment_to_homeActivity);
    }




}