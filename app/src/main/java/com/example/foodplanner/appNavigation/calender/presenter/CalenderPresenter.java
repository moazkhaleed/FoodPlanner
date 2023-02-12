package com.example.foodplanner.appNavigation.calender.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.appNavigation.calender.view.CalenderViewInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;

import java.util.List;

public class CalenderPresenter implements CalenderPresenterInterface{

    private static final String TAG = "CalenderPresenter";
    private RepositoryInterface repositoryInterface;
    private CalenderViewInterface calenderViewInterface;

    public CalenderPresenter(CalenderViewInterface calenderViewInterface, RepositoryInterface repositoryInterface) {
        this.calenderViewInterface = calenderViewInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public LiveData<List<Meal>> getAllScheduledMeals() {
        return repositoryInterface.getScheduledMeas();
    }

    @Override
    public void removeFavouriteMeal(Meal meal) {
        if(meal.isFav() == false)
            repositoryInterface.removeMeal(meal);
        else {
            meal.setDate("");
            repositoryInterface.insertMeal(meal);
        }
    }
}
