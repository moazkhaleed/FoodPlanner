package com.example.foodplanner.models;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.CategoryNetworkDelegate;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    void insertMeal(Meal meal);
    void removeMeal(Meal meal);
    LiveData<List<Meal>> getMealsDB();
    void getRandomMeal(NetworkDelegate networkDelegate);
    void getMealDetails(NetworkDelegate networkDelegate,String id);
    LiveData<List<Meal>> getStoredDetails(String id);

    void getCategories(CategoryNetworkDelegate networkDelegate);
    void getMealsByName(NetworkDelegate networkDelegate,String name);
    void getMealsByCategory(NetworkDelegate networkDelegate,String category);
    void getMealsByIngredient(NetworkDelegate networkDelegate,String ingredient);
    void getMealsByCountry(NetworkDelegate networkDelegate,String country);


}
