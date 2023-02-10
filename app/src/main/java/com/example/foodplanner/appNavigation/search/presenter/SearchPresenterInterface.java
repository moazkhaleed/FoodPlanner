package com.example.foodplanner.appNavigation.search.presenter;

public interface SearchPresenterInterface {
    void getCategories();
    void getMealsByCategory(String categoryName);
}
