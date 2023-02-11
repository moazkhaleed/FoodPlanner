package com.example.foodplanner.auth.favourites.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.auth.favourites.view.FavViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;

import java.util.List;

public class FavMealPresenter implements FavMealPresenterInterface{
    private static final String TAG = "FavouriteMealPresent";
    private RepositoryInterface repositoryInterface;

    FavViewerInterface favViewerInterface;

    public FavMealPresenter(FavViewerInterface favViewerInterface, RepositoryInterface repositoryInterface) {
        this.favViewerInterface = favViewerInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void removeFavouriteMeal(Meal meal) {
        repositoryInterface.removeMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeals() {
        return repositoryInterface.getMealsDB();
    }
}
