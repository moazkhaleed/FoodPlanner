package com.example.foodplanner.category.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.category.presenter.CategoryPresenter;
import com.example.foodplanner.category.presenter.CategoryPresenterInterface;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.utils.Utils;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryViewerInterface,onMealClickListener {


    RecyclerView recyclerView;
    ProgressBar progressBar;
    AlertDialog.Builder descDialog;
    CategoryPresenterInterface presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.categoryRecyclerView);
        progressBar = view.findViewById(R.id.categoryProgressBar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            String categoryName = getArguments().getString("EXTRA_DATA_NAME");
             presenter = new CategoryPresenter(this,
                    Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(this.getActivity().getApplicationContext()),this.getActivity().getApplicationContext()),
                    categoryName);
            presenter.getMealsByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meal> meals) {
        RecyclerViewMealByCategory adapter = 
                new RecyclerViewMealByCategory(getActivity(), meals,this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        
        /*adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
            intent.putExtra("id",meals.get(position).getIdMeal());
            intent.putExtra("source","category");
            startActivity(intent);
        });*/
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

    @Override
    public void addFavor(Meal meal) {
        presenter.addFavouriteMeal(meal);
    }
}
