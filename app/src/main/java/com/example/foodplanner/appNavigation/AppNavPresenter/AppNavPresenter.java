package com.example.foodplanner.appNavigation.AppNavPresenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.auth.signup.presenter.SignupPresenter;
import com.example.foodplanner.models.User;
import com.google.gson.Gson;

public class AppNavPresenter implements AppNavPresenterInterface{

    public AppNavPresenter() {}

    @Override
    public User getLocalUserData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("user", null);
        User user = gson.fromJson(json, User.class);
        return  user;
    }
}
