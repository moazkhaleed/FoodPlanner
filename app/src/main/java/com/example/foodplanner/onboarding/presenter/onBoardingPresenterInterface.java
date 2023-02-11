package com.example.foodplanner.onboarding.presenter;

import android.content.Context;

import com.example.foodplanner.models.User;

public interface onBoardingPresenterInterface {
     boolean onBoardingFinished(Context context);
     User getLocalUserData(Context context);
}
