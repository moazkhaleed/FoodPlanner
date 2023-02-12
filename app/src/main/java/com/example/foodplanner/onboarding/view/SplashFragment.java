package com.example.foodplanner.onboarding.view;

import static androidx.navigation.Navigation.findNavController;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.AppNavigationActivity;
import com.example.foodplanner.auth.AuthActivity;
import com.example.foodplanner.onboarding.presenter.onBoardingPresenter;
import com.example.foodplanner.onboarding.presenter.onBoardingPresenterInterface;
import com.google.firebase.auth.FirebaseAuth;


public class SplashFragment extends Fragment {

 private onBoardingPresenterInterface presenter;

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
         presenter = new onBoardingPresenter();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(presenter.onBoardingFinished(getContext())){
                        if(presenter.getLocalUserData(getContext()) != null || FirebaseAuth.getInstance().getCurrentUser()!=null){
                            Intent i = new Intent(getActivity(), AppNavigationActivity.class);
                            startActivity(i);
                        }else{
                            Intent i = new Intent(getActivity(), AuthActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }


                    }else{
                        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_viewPagerFragment);
                    }
                }
            },3000);
        }


}