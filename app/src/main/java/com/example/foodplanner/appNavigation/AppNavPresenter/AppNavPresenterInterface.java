package com.example.foodplanner.appNavigation.AppNavPresenter;

import android.content.Context;

import com.example.foodplanner.models.User;

public interface AppNavPresenterInterface {
    User getLocalUserData(Context context);
}
