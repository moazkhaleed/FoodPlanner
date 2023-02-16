package com.example.foodplanner.appNavigation.profile.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.User;

import java.util.List;

public interface ProfilePresenterInterface {

    User getData(Context context);
    void deleteData(Context context);

    LiveData<List<Meal>> getAllFavMeals();

    void addMealsToFirebase(List<Meal> meals);
    void getMealsFirebase();

}
