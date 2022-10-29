package com.hanaahany.finalprojectorange.ui.fragment.login;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.FragmentResetPasswordBinding;


public class ResetPasswordFragment extends Fragment {
    FragmentResetPasswordBinding binding;
    final static String DATA_RECEIVE = "data_receive";
    private static final String TAG = "ResetPasswordFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding=FragmentResetPasswordBinding.inflate(getLayoutInflater(),container,false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onClicks();
    }

    private void onClicks() {
        binding.btnNextForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_resetPasswordFragment_to_successPasswordFragment);
//                FirebaseAuth auth = FirebaseAuth.getInstance();
//                String emailAddress = "hanaahany822@gmail.com";
//
//// To apply the default app language instead of explicitly setting it.
//// auth.useAppLanguage();
//                auth.sendPasswordResetEmail(emailAddress)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                   // Log.d(TAG, "Email sent.");
//                                    Log.i(TAG, "onComplete:Email sent. ");
//                                }
//                            }
//                        });
            }
        });
    }

}