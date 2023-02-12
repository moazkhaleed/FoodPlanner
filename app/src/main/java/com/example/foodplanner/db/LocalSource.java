package com.example.foodplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class LocalSource implements LocalSourceInterface{

    private MealDao mealDao;
    private static LocalSource localSource = null;
    private LiveData<List<Meal>> storedMeals;

    private LocalSource(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDao = appDataBase.mealDao();
        storedMeals = mealDao.getAllMeals();
    }

    public static LocalSource getInstance(Context context) {
        if (localSource == null)
            localSource = new LocalSource(context);
        return localSource;
    }


    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.insertMeal(meal);
            }
        }).start();

    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getAllMealsStored() {
        return storedMeals;
    }

    @Override
    public LiveData<List<Meal>>  getDetailsMealStored(String id) {
        return mealDao.findMealById(Integer.parseInt(id));
    }

    @Override
    public LiveData<List<Meal>> getAllScheduledMeals() {
        return mealDao.getScheduledMeas();
    }
}
