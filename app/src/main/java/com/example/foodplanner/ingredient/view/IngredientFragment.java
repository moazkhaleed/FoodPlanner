package com.example.foodplanner.ingredient.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.ingredient.presenter.IngredientPresenter;
import com.example.foodplanner.ingredient.presenter.IngredientPresenterInterface;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.utils.Utils;

import java.util.List;

public class IngredientFragment extends Fragment implements IngredientViewerInterface{

    RecyclerView recyclerView;
    ProgressBar progressBar;
    AlertDialog.Builder descDialog;

    IngredientPresenterInterface categoryPresenterInterface;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        recyclerView = view.findViewById(R.id.ingredientRecyclerView);
        progressBar = view.findViewById(R.id.ingredientProgressBar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            String ingredientName = getArguments().getString("EXTRA_DATA_NAME");
            IngredientPresenter presenter = new IngredientPresenter(this,
                    Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(this.getActivity().getApplicationContext()),this.getActivity().getApplicationContext()),
                    ingredientName);
            presenter.getMealsByIngredient(getArguments().getString("EXTRA_DATA_NAME"));
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
        RecyclerViewMealByIngredient adapter =
                new RecyclerViewMealByIngredient(getActivity(), meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
            intent.putExtra("id",meals.get(position).idMeal);
            intent.putExtra("source","ingredient");
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }
}