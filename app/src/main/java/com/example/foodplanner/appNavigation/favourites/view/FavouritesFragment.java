package com.example.foodplanner.appNavigation.favourites.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.MealDetails.view.OnMealClicked;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.favourites.presenter.FavMealPresenter;
import com.example.foodplanner.appNavigation.favourites.presenter.FavMealPresenterInterface;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;

import java.util.List;

public class FavouritesFragment extends Fragment implements FavViewerInterface, onFavMealClickListener  {
    private RecyclerView favRV;
    private LinearLayoutManager layoutManager;
    private FavMealAdapter adapter;
    private FavMealPresenterInterface favMealPresenterInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favRV = view.findViewById(R.id.favRecycler);
        favRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favRV.setLayoutManager(layoutManager);
        adapter = new FavMealAdapter(getContext(),this,onMealClicked);

        favMealPresenterInterface =  new FavMealPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));
        favMealPresenterInterface.getAllFavMeals().observe(getActivity(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                adapter.setFavMealsList(meals);
                adapter.notifyDataSetChanged();
                favRV.setAdapter(adapter);
            }
        });
    }

    @Override
    public void remove(Meal meal) {
        favMealPresenterInterface.removeFavouriteMeal(meal);
        adapter.notifyDataSetChanged();

    }

    private final OnMealClicked onMealClicked= new OnMealClicked() {
        @Override
        public void onMealClicked(String id) {
            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("source","fav");
            startActivity(intent);
        }
    };
}