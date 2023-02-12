package com.example.foodplanner.appNavigation.home.presenter;

import com.example.foodplanner.models.Meal;

public interface RandomMealPresenterInterface {
     void addFavouriteMeal(Meal meal);
     void getMeals();

     void getMealsByName(String name);
}
