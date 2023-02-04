package com.example.foodplanner.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.AppNavigationActivity;

public class AuthActivity extends AppCompatActivity {
    Button logninBtn;
    Button signupBtn;
    Button skipBtn;
    FragmentManager mgr;
    FragmentTransaction transaction;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        logninBtn = findViewById(R.id.logninBtn);
        signupBtn = findViewById(R.id.signupBtn);
        skipBtn = findViewById(R.id.skipBtn);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this, AppNavigationActivity.class);
                startActivity(intent);
            }
        });

        logninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new LogInFragment();
                mgr = getSupportFragmentManager();
                transaction = mgr.beginTransaction();
                transaction.replace(R.id.fragmentContainerId,fragment,"loginFragment");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new SignupFragment();
                mgr = getSupportFragmentManager();
                transaction = mgr.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragmentContainerId,fragment,"loginFragment");

                transaction.commit();
            }
        });

    }
}