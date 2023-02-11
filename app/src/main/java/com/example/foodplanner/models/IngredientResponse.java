package com.example.foodplanner.models;

import java.util.ArrayList;
import java.util.List;

public class IngredientResponse {

    private ArrayList<Ingredient> meals;

    public ArrayList<Ingredient> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Ingredient> meals) {
        this.meals = meals;
    }
}