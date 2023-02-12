package com.example.foodplanner.category.presenter;

import com.example.foodplanner.models.Meal;

public interface CategoryPresenterInterface {
    void getMealsByCategory(String category);
    void addFavouriteMeal(Meal meal);
}
