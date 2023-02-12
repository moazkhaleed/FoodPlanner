package com.example.foodplanner.appNavigation.calender.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface CalenderPresenterInterface {

    LiveData<List<Meal>> getAllScheduledMeals();
    void removeFavouriteMeal(Meal meal);
}
