package com.example.foodplanner.models;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    void insertMeal(Meal meal);
    void removeMeal(Meal meal);
    LiveData<List<Meal>> getMealsDB();
    void getRandomMeal(NetworkDelegate networkDelegate);
    void getMealDetails(NetworkDelegate networkDelegate,String id);

}
