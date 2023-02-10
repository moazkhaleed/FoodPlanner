package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.models.Meal;

@Database(entities = {Meal.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase appDataBase = null;

    public abstract MealDao mealDao();

    public synchronized static AppDataBase getInstance(Context context) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "Meals").build();
        }
        return appDataBase;
    }
}
