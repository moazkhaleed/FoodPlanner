package com.example.foodplanner.ingredient.presenter;

import com.example.foodplanner.models.Meal;

public interface IngredientPresenterInterface {
    void getMealsByIngredient(String ingredient);
    void addFavouriteMeal(Meal meal);
}
