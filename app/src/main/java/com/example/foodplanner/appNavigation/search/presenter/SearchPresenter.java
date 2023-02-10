package com.example.foodplanner.appNavigation.search.presenter;


import com.example.foodplanner.appNavigation.search.view.SearchViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;


public class SearchPresenter implements NetworkDelegate,SearchPresenterInterface {

    private SearchViewerInterface viewerInterface;
    private RepositoryInterface repositoryInterface;


    public SearchPresenter(SearchViewerInterface searchViewerInterface, RepositoryInterface repositoryInterface) {
        this.viewerInterface = searchViewerInterface;
        this.repositoryInterface = repositoryInterface;
    }

    void getMealsByName(String name) {
        viewerInterface.showLoading();
        repositoryInterface.getMealsByName(this,name);
    }

    @Override
    public void getMealsByCategory(String categoryName) {
        viewerInterface.showLoading();
        repositoryInterface.getMealsByCategory(this, categoryName);
    }


    public void getCategories() {
        viewerInterface.showLoading();
        repositoryInterface.getCategories(this);

    }

    @Override
    public void onSuccess(List<Meal> meals) {
        viewerInterface.hideLoading();
        viewerInterface.setMeal(meals);
    }

    @Override
    public void onFailure(String error) {
        viewerInterface.hideLoading();
        viewerInterface.onErrorLoading(error);
    }
}
