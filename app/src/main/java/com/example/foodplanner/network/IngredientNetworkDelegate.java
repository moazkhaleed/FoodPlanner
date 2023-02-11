package com.example.foodplanner.network;

import com.example.foodplanner.models.Ingredient;

import java.util.List;

public interface IngredientNetworkDelegate {
    void onSuccessIngredients(List<Ingredient> ingredients);
    void onFailureIngredients(String error);
}
