package com.example.foodplanner.models;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.CategoryNetworkDelegate;
import com.example.foodplanner.network.CountryNetworkDelegate;
import com.example.foodplanner.network.IngredientNetworkDelegate;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.TrendingNetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    void insertMeal(Meal meal);
    void removeMeal(Meal meal);
    LiveData<List<Meal>> getMealsDB();
    void getRandomMeal(NetworkDelegate networkDelegate);
    void getMealDetails(NetworkDelegate networkDelegate,String id);
    LiveData<List<Meal>> getStoredDetails(String id);

    void getCategories(CategoryNetworkDelegate networkDelegate);
    void getIngredients(IngredientNetworkDelegate networkDelegate);
    void getCountries(CountryNetworkDelegate networkDelegate);
    void getMealsByName(TrendingNetworkDelegate networkDelegate, String name);
    void getMealsByCategory(NetworkDelegate networkDelegate,String category);
    void getMealsByIngredient(NetworkDelegate networkDelegate,String ingredient);
    void getMealsByCountry(NetworkDelegate networkDelegate,String country);
    LiveData<List<Meal>>  getScheduledMeas();
    LiveData<List<Meal>>  getFavMeas();


}
