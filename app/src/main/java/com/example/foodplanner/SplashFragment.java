package com.example.foodplanner;

import static androidx.navigation.Navigation.findNavController;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SplashFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(onBoardingFinished()){
                        Intent i = new Intent(getActivity(), AuthActivity.class);
                        startActivity(i);
                    }else{
                        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_viewPagerFragment);
                    }
                }
            },3000);
        }



    private boolean onBoardingFinished(){
        SharedPreferences sh = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE);

        return sh.getBoolean("finished",false);
    }
}