package com.example.foodplanner.appNavigation.favourites.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.appNavigation.favourites.view.FavViewerInterface;
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
        if(meal.getDate() == null) {
            repositoryInterface.removeMeal(meal);
        } else{
            meal.setFav(false);
            repositoryInterface.insertMeal(meal);
        }
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeals() {
        return repositoryInterface.getFavMeas();
    }
}
