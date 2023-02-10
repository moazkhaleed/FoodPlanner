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

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.search.presenter.SearchPresenter;
import com.example.foodplanner.appNavigation.search.presenter.SearchPresenterInterface;
import com.example.foodplanner.category.view.CategoryActivity;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.utils.Utils;

import java.io.Serializable;
import java.util.List;

public class SearchFragment extends Fragment implements SearchViewerInterface {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";


    ViewPager viewPagerMeal;
    RecyclerView recyclerViewCategory;

    SearchPresenter presenter;
    private View shimmerMeal;
    private View shimmerCategory;
    private CardView cardSearch;

    SearchPresenterInterface presenterInterface;

    private View mealRecyclerCard;


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

        viewPagerMeal = view.findViewById(R.id.viewPagerHeader);
        recyclerViewCategory = view.findViewById(R.id.recyclerCategory);
        shimmerMeal = view.findViewById(R.id.shimmerMeal);
        shimmerCategory = view.findViewById(R.id.shimmerCategory);
        cardSearch = view.findViewById(R.id.searchTextCard);
        mealRecyclerCard = view.findViewById(R.id.mealRecyclerCard);

        cardSearch.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), SearchByNameActivity.class);
            startActivity(intent);
        });

        presenterInterface = new SearchPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), this.getContext()));

        presenterInterface.getMealsByName("");
        presenterInterface.getCategories();
    }

    @Override
    public void showLoading() {
        shimmerMeal.setVisibility(View.VISIBLE);
        shimmerCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerMeal.setVisibility(View.GONE);
        shimmerCategory.setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meal> meals) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meals, getContext());
        viewPagerMeal.setAdapter(headerAdapter);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
            intent.putExtra("id",meals.get(position).idMeal);
            startActivity(intent);
        });
    }

    @Override
    public void setCategory(List<Category> categories) {
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
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getContext(), "Title", message);
    }
}