package com.example.foodplanner.category.presenter;

import com.example.foodplanner.category.view.CategoryViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class CategoryPresenter implements NetworkDelegate, CategoryPresenterInterface {
    private CategoryViewerInterface viewerInterface;

    private RepositoryInterface repositoryInterface;
    String categoryName;
    private static final String TAG = "CategoryPresenter";
    public CategoryPresenter(
            CategoryViewerInterface categoryViewerInterface,
            RepositoryInterface repositoryInterface,
            String categoryName) {
        this.viewerInterface = categoryViewerInterface;
        this.repositoryInterface = repositoryInterface;
        this.categoryName = categoryName;
    }
    @Override
    public void getMealsByCategory(String category) {
        viewerInterface.showLoading();
        repositoryInterface.getMealsByCategory(this, categoryName);
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
