package com.example.foodplanner.network;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface NetworkDelegate {
    void onSuccess(List<Meal> meals);
    void onFailure(String error);
}
