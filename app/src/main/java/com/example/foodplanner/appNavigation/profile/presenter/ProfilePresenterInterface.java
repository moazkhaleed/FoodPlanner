package com.example.foodplanner.appNavigation.profile.presenter;

import android.content.Context;

import com.example.foodplanner.models.User;

public interface ProfilePresenterInterface {

    User getData(Context context);
    void deleteData(Context context);
}
