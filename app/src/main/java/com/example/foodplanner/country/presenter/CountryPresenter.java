package com.example.foodplanner.country.presenter;

import com.example.foodplanner.country.view.CountryViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class CountryPresenter implements NetworkDelegate, CountryPresenterInterface {
    private CountryViewerInterface viewerInterface;

    private RepositoryInterface repositoryInterface;
    String categoryName;
    private static final String TAG = "CategoryPresenter";

    public CountryPresenter(
            CountryViewerInterface viewerInterface,
            RepositoryInterface repositoryInterface,
            String categoryName) {
        this.viewerInterface = viewerInterface;
        this.repositoryInterface = repositoryInterface;
        this.categoryName = categoryName;
    }

    @Override
    public void getMealsByCountry(String countryName) {
        viewerInterface.showLoading();
        repositoryInterface.getMealsByCountry(this, countryName);
    }

    @Override
    public void onSuccess(List<Meal> meals) {
        viewerInterface.hideLoading();
        viewerInterface.setMeals(meals);
    }

    @Override
    public void onFailure(String error) {
        viewerInterface.hideLoading();
        viewerInterface.onErrorLoading(error);
    }
}
