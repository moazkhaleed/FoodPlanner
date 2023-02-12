package com.example.foodplanner.appNavigation.search.view;

import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Country;
import com.example.foodplanner.models.Ingredient;
import com.example.foodplanner.models.Meal;

import java.util.List;

public interface SearchViewerInterface {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meal> meal);
    void setCategories(List<Category> category);
    void setIngredients(List<Ingredient> ingredients);
    void setCountries(List<Country> countries);
    void onErrorLoading(String message);
}
