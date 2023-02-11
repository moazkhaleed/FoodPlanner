package com.example.foodplanner.country.view;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface CountryViewerInterface {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meal> meals);
    void onErrorLoading(String message);
}
