package com.example.foodplanner.appNavigation.search.view;

import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;

import java.util.List;

public interface SearchViewerInterface {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meal> meal);
    void setCategory(List<Category> category);
    void onErrorLoading(String message);
}
