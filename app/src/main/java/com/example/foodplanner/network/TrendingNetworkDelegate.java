package com.example.foodplanner.network;

import com.example.foodplanner.models.Meal;

import java.util.List;

public interface TrendingNetworkDelegate {
    void onSuccessTrending(List<Meal> meals);
    void onFailureTrending(String error);
}
