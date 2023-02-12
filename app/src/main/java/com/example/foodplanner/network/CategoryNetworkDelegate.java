package com.example.foodplanner.network;

import com.example.foodplanner.models.Category;

import java.util.List;

public interface CategoryNetworkDelegate {
    void onSuccessCategory(List<Category> objects);
    void onFailureCategory(String error);
}
