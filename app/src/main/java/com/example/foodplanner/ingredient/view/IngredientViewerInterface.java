package com.example.foodplanner.ingredient.view;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface IngredientViewerInterface {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meal> meals);
    void onErrorLoading(String message);
}
