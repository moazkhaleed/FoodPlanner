package com.example.foodplanner.auth.favourites.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface FavMealPresenterInterface {

    public void removeFavouriteMeal(Meal meal);

    public LiveData<List<Meal>> getAllFavMeals();
}
