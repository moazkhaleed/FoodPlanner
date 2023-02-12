package com.example.foodplanner.appNavigation.home.presenter;

import android.util.Log;

import com.example.foodplanner.appNavigation.home.view.RandomViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.TrendingNetworkDelegate;

import java.util.List;

public class RandomMealPresenter implements RandomMealPresenterInterface, NetworkDelegate, TrendingNetworkDelegate {
    private RandomViewerInterface randomViewerInterface;
    private RepositoryInterface repositoryInterface;
    private static final String TAG = "AllProductsPresenter";
    public RandomMealPresenter(RandomViewerInterface randomViewerInterface, RepositoryInterface repositoryInterface) {
        this.randomViewerInterface = randomViewerInterface;
        this.repositoryInterface=repositoryInterface;
    }



    @Override
    public void addFavouriteMeal(Meal meal) {

        repositoryInterface.insertMeal(meal);
    }

    @Override
    public void getMeals() {

        repositoryInterface.getRandomMeal(this);
    }

    @Override
    public void getMealsByName(String name) {
        repositoryInterface.getMealsByName(this,name);
    }

    @Override
    public void onSuccess(List<Meal> randomMeal) {

        randomViewerInterface.showMeals(randomMeal);
    }

    @Override
    public void onFailure(String error) {
        Log.d(TAG, "onFailure: no Meals");
    }

    @Override
    public void onSuccessTrending(List<Meal> meals) {

        randomViewerInterface.showTrending(meals);
    }

    @Override
    public void onFailureTrending(String error) {
        Log.d(TAG, "onFailure: no Meals");
    }
}
