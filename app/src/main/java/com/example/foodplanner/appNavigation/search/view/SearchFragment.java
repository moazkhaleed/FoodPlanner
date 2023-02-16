package com.example.foodplanner.appNavigation.search.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.search.presenter.SearchPresenter;
import com.example.foodplanner.appNavigation.search.presenter.SearchPresenterInterface;
import com.example.foodplanner.category.view.CategoryActivity;
import com.example.foodplanner.country.view.CountryActivity;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.ingredient.view.IngredientActivity;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Country;
import com.example.foodplanner.models.Ingredient;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchViewerInterface {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_INGREDIENT = "ingredient";
    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_POSITION = "position";


    ViewPager viewPagerMeal;
    RecyclerView recyclerViewCategory;

    SearchPresenter presenter;
    private View shimmerMeal;
    private View shimmerCategory;
    private CardView cardSearch;


    SearchPresenterInterface presenterInterface;

    private View mealRecyclerCard;

    RecyclerView recyclerViewIngredient;

    private View shimmerIngredient;

    RecyclerView recyclerViewCountry;

    private View shimmerCountry;
    private List<Meal> allMealsByName;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allMealsByName= new ArrayList<>();

        viewPagerMeal = view.findViewById(R.id.viewPagerHeader);
        recyclerViewCategory = view.findViewById(R.id.recyclerCategory);
        shimmerMeal = view.findViewById(R.id.shimmerMeal);
        shimmerCategory = view.findViewById(R.id.shimmerCategory);
        cardSearch = view.findViewById(R.id.searchTextCard);
        mealRecyclerCard = view.findViewById(R.id.mealRecyclerCard);

        recyclerViewIngredient= view.findViewById(R.id.recyclerIngredient);
        shimmerIngredient = view.findViewById(R.id.shimmerIngredient);

        recyclerViewCountry= view.findViewById(R.id.recyclerCountry);
        shimmerCountry = view.findViewById(R.id.shimmerCountry);

        cardSearch.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), SearchByNameActivity.class);
            intent.putExtra("mealsList", (Serializable) allMealsByName);
            startActivity(intent);
        });

        presenterInterface = new SearchPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), this.getContext()));

        presenterInterface.getMealsByName("");
        presenterInterface.getCategories();
        presenterInterface.getIngredients();
        presenterInterface.getCountries();
    }

    @Override
    public void showLoading() {
        shimmerMeal.setVisibility(View.VISIBLE);
        shimmerCategory.setVisibility(View.VISIBLE);
        shimmerIngredient.setVisibility(View.VISIBLE);
        shimmerCountry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerMeal.setVisibility(View.GONE);
        shimmerCategory.setVisibility(View.GONE);
        shimmerIngredient.setVisibility(View.GONE);
        shimmerCountry.setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meal> meals) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meals, getContext());
        viewPagerMeal.setAdapter(headerAdapter);
        headerAdapter.notifyDataSetChanged();

        allMealsByName = meals;

        headerAdapter.setOnItemClickListener((v, position) -> {
            Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
            intent.putExtra("id",meals.get(position).idMeal);
            intent.putExtra("source","search");
            startActivity(intent);
        });
    }

    @Override
    public void setCategories(List<Category> categories) {
        System.out.println("setCategory:" + categories.size());
        RecyclerViewSearchAdapter searchAdapter = new RecyclerViewSearchAdapter(categories, getContext());
        recyclerViewCategory.setAdapter(searchAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        searchAdapter.notifyDataSetChanged();

        searchAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getContext(), CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) categories);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void setIngredients(List<Ingredient> ingredients) {
        System.out.println("setIngredients:" + ingredients.size());
        RecyclerViewIngredientSearchAdapter adapter = new RecyclerViewIngredientSearchAdapter(ingredients.subList(0,30), getContext());
        recyclerViewIngredient.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerViewIngredient.setLayoutManager(layoutManager);
        recyclerViewIngredient.setNestedScrollingEnabled(true);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getContext(), IngredientActivity.class);
            intent.putExtra(EXTRA_INGREDIENT, (Serializable) ingredients);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void setCountries(List<Country> countries) {
        System.out.println("setCountries:" + countries.size());
        RecyclerViewCountriesSearchAdapter adapter = new RecyclerViewCountriesSearchAdapter(countries, getContext());
        recyclerViewCountry.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);

        recyclerViewCountry.setLayoutManager(layoutManager);
        recyclerViewCountry.setNestedScrollingEnabled(true);

        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getContext(), CountryActivity.class);
            intent.putExtra(EXTRA_COUNTRY, (Serializable) countries);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getContext(), "Title", message);
    }
}

