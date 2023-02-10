package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface LocalSourceInterface {

    public void insertMeal(Meal meal);

    public void removeMeal(Meal meal);

    public LiveData<List<Meal>> getAllMealsStored();
    public  LiveData<List<Meal>>  getDetailsMealStored(String id);
}
