package com.example.foodplanner.country.presenter;

import com.example.foodplanner.models.Meal;

public interface CountryPresenterInterface {
    void getMealsByCountry(String ingredient);
    void addFavouriteMeal(Meal meal);
}
