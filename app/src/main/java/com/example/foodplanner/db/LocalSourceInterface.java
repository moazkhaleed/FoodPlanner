package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface LocalSourceInterface {

    void insertMeal(Meal meal);

    void removeMeal(Meal meal);

    LiveData<List<Meal>> getAllMealsStored();
    LiveData<List<Meal>>  getDetailsMealStored(String id);
    LiveData<List<Meal>> getAllScheduledMeals();
}
