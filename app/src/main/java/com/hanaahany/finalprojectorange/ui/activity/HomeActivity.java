package com.hanaahany.finalprojectorange.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.hanaahany.finalprojectorange.R;
import com.hanaahany.finalprojectorange.databinding.ActivityHomeBinding;
import com.hanaahany.finalprojectorange.ui.fragment.main.home.ProfileFragment;
import com.hanaahany.finalprojectorange.ui.fragment.main.home.cart.CartFragment;
import com.hanaahany.finalprojectorange.ui.fragment.main.HomeFragment;

public class HomeActivity extends AppCompatActivity {
ActivityHomeBinding binding;
NavController navController;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changeFragment(new HomeFragment());

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int id=item.getItemId();
            switch (id){
                case R.id.home_item:
                    changeFragment(new HomeFragment());
                    break;
                case R.id.cart_item:
                    changeFragment(new CartFragment());
                    break;
                case R.id.account_item:
                    changeFragment(new ProfileFragment());
                    break;
            }
            return false;
        });
    }
    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment,fragment)
                .commit();
    }
}