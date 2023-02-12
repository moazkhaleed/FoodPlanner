package com.example.foodplanner.ingredient.presenter;

import com.example.foodplanner.ingredient.view.IngredientViewerInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;

import java.util.List;

public class IngredientPresenter implements NetworkDelegate, IngredientPresenterInterface {
    private IngredientViewerInterface viewerInterface;

    private RepositoryInterface repositoryInterface;
    String categoryName;

    public IngredientPresenter(
            IngredientViewerInterface viewerInterface,
            RepositoryInterface repositoryInterface,
            String categoryName) {
        this.viewerInterface = viewerInterface;
        this.repositoryInterface = repositoryInterface;
        this.categoryName = categoryName;
    }
    @Override
    public void getMealsByIngredient(String ingredientName) {
        viewerInterface.showLoading();
        repositoryInterface.getMealsByIngredient(this, ingredientName);
    }

    @Override
    public void addFavouriteMeal(Meal meal) {
        repositoryInterface.insertMeal(meal);
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
