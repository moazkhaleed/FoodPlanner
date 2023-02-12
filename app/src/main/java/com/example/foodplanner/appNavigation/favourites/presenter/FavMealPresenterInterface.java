package com.example.foodplanner.appNavigation.favourites.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface FavMealPresenterInterface {

    void removeFavouriteMeal(Meal meal);

    LiveData<List<Meal>> getAllFavMeals();
}
