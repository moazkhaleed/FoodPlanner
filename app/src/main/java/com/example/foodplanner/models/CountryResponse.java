package com.example.foodplanner.models;

import java.util.ArrayList;

public class CountryResponse {

    private ArrayList<Country> meals;

    public ArrayList<Country> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Country> meals) {
        this.meals = meals;
    }
}