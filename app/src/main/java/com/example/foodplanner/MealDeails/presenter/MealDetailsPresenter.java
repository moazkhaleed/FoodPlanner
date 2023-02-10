package com.example.foodplanner.MealDeails.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.MealDeails.view.MealDetailsViewerInterface;
import com.example.foodplanner.appNavigation.home.view.RandomViewerInterface;
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
     public LiveData<List<Meal>> getDetailsFromRoom() {
        return  repositoryInterface.getStoredDetails(id);
    }

    @Override
    public void onSuccess(List<Meal> randomMeal) {
          mealDetailsViewerInterface.showDetails(randomMeal);
    }

    @Override
    public void onFailure(String error) {
        Log.d(TAG, "onFailure: no Meals");
    }
}
