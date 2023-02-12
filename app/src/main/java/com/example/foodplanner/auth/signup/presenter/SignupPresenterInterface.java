package com.example.foodplanner.auth.signup.presenter;

import android.content.Context;

import com.example.foodplanner.models.User;

public interface SignupPresenterInterface {
    void saveUserDataLocally(Context context, User user);
}
