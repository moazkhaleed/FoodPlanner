package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.models.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("SELECT * FROM Meals")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT * FROM Meals WHERE idMeal = :first")
    LiveData<List<Meal>>  findMealById(int first);

    @Query("SELECT * FROM Meals WHERE date IS NOT NULL")
    LiveData<List<Meal>>  getScheduledMeas();
}
