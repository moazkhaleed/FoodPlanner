package com.example.foodplanner.appNavigation.search.presenter;


import com.example.foodplanner.appNavigation.search.view.SearchViewerInterface;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Country;
import com.example.foodplanner.models.Ingredient;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.CountryNetworkDelegate;
import com.example.foodplanner.network.IngredientNetworkDelegate;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.CategoryNetworkDelegate;

import java.util.List;


public class SearchPresenter implements NetworkDelegate, CategoryNetworkDelegate, IngredientNetworkDelegate, CountryNetworkDelegate,SearchPresenterInterface {

    private SearchViewerInterface viewerInterface;
    private RepositoryInterface repositoryInterface;


    public SearchPresenter(SearchViewerInterface searchViewerInterface, RepositoryInterface repositoryInterface) {
        this.viewerInterface = searchViewerInterface;
        this.repositoryInterface = repositoryInterface;
    }
    @Override
    public void getMealsByName(String name) {
        viewerInterface.showLoading();
        repositoryInterface.getMealsByName(this,name);
    }

    @Override
    public void getMealsByCategory(String categoryName) {
        viewerInterface.showLoading();
        repositoryInterface.getMealsByCategory(this, categoryName);
    }

    @Override
    public void getCategories() {
        viewerInterface.showLoading();
        repositoryInterface.getCategories(this);

    }

    @Override
    public void getIngredients() {
        viewerInterface.showLoading();
        repositoryInterface.getIngredients(this);

    }

    @Override
    public void getCountries() {
        viewerInterface.showLoading();
        repositoryInterface.getCountries(this);

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

    @Override
    public void onSuccessCategory(List<Category> categories) {
        viewerInterface.hideLoading();
        viewerInterface.setCategories(categories);
    }

    @Override
    public void onFailureCategory(String error) {
        viewerInterface.hideLoading();
        viewerInterface.onErrorLoading(error);
    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {
        viewerInterface.hideLoading();
        viewerInterface.setIngredients(ingredients);
    }

    @Override
    public void onFailureIngredients(String error) {
        viewerInterface.hideLoading();
        viewerInterface.onErrorLoading(error);

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {
        viewerInterface.hideLoading();
        viewerInterface.setCountries(countries);
    }

    @Override
    public void onFailureCountries(String error) {
        viewerInterface.hideLoading();
        viewerInterface.onErrorLoading(error);
    }
}
