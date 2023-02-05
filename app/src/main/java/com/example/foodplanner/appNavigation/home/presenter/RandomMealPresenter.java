package com.example.foodplanner.appNavigation.home.presenter;

import android.util.Log;

import com.example.foodplanner.appNavigation.home.view.RandomViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class RandomMealPresenter implements RandomMealPresenterInterface, NetworkDelegate {
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
    public void onSuccess(List<Meal> randomMeal) {
        randomViewerInterface.showMeals(randomMeal);
    }

    @Override
    public void onFailure(String error) {
        Log.d(TAG, "onFailure: no Meals");
    }
}
