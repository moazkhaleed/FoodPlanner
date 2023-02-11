package com.example.foodplanner.appNavigation.profile.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.auth.signup.presenter.SignupPresenter;
import com.example.foodplanner.models.User;
import com.google.gson.Gson;

public class ProfilePresenter implements ProfilePresenterInterface{
   User user = new User();
    @Override
    public User getData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("user", null);
        user = gson.fromJson(json, User.class);
        return user;
    }

    @Override
    public void deleteData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }
}
