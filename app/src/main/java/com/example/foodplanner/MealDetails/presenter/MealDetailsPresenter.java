package com.example.foodplanner.MealDetails.presenter;

import android.util.Log;

import com.example.foodplanner.MealDetails.view.MealDetailsViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class MealDetailsPresenter implements NetworkDelegate,MealDetailsPresenterInterface {

    private MealDetailsViewerInterface mealDetailsViewerInterface;
    private RepositoryInterface repositoryInterface;
    String id;
    private static final String TAG = "MealDetailsPresenter";

    public MealDetailsPresenter(MealDetailsViewerInterface mealDetailsViewerInterface, RepositoryInterface repositoryInterface,String id) {
        this.mealDetailsViewerInterface = mealDetailsViewerInterface;
        this.repositoryInterface = repositoryInterface;
        this.id = id;
    }

    @Override
    public void getDetails() {
         repositoryInterface.getMealDetails(this,id);
    }

    @Override
    public void onSuccess(List<Meal> randomMeal) {
          mealDetailsViewerInterface.showDetails(randomMeal);
    }

    @Override
    public void onFailure(String error) {
        Log.d(TAG, "mealDetails onFailure: no Meals");
    }
}
