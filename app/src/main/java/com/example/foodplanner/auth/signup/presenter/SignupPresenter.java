package com.example.foodplanner.auth.signup.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.models.User;
import com.google.gson.Gson;

public class SignupPresenter implements SignupPresenterInterface{

    public static final String PREF_Name ="PREF";


    @Override
    public void saveUserDataLocally(Context context,User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("user", json);
        editor.commit();
    }
}
