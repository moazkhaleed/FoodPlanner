package com.example.foodplanner.appNavigation.search.presenter;

public interface SearchPresenterInterface {
    void getCategories();
    void getIngredients();
    void getCountries();
    void getMealsByCategory(String categoryName);

    void getMealsByName(String name);

}
