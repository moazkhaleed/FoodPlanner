package com.example.foodplanner.MealDeails.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface MealDetailsPresenterInterface {
    void getDetails();
    public LiveData<List<Meal>> getDetailsFromRoom();
}
