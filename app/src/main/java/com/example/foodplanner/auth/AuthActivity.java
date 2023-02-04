package com.example.foodplanner.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodplanner.R;

public class AuthActivity extends AppCompatActivity {
    Button logninBtn;
    Button signupBtn;
    FragmentManager mgr;
    FragmentTransaction transaction;
    SignupFragment signupFragment;
    LogInFragment logInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        logninBtn = findViewById(R.id.logninBtn);
        signupBtn = findViewById(R.id.signupBtn);
        mgr = getSupportFragmentManager();
        transaction = mgr.beginTransaction();

        if(savedInstanceState == null){
            signupFragment = new SignupFragment();
            logInFragment = new LogInFragment();


        } else {
            logInFragment = (LogInFragment) mgr.findFragmentByTag("loginFragment");
            signupFragment = (SignupFragment) mgr.findFragmentByTag("SignupFragment");

        }

        transaction.add(R.id.fragmentContainerId,logInFragment,"loginFragment");
        transaction.add(R.id.fragmentContainerId,signupFragment,"SignupFragment");
        logninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.replace(R.id.fragmentContainerId,logInFragment);
                //transaction.commit();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.replace(R.id.fragmentContainerId,signupFragment);
                transaction.commit();
            }
        });

    }
}