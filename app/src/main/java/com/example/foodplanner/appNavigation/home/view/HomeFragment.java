package com.example.foodplanner.appNavigation.home.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.MealDetails.view.OnMealClicked;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.home.presenter.RandomMealPresenter;
import com.example.foodplanner.appNavigation.home.presenter.RandomMealPresenterInterface;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;

import java.util.List;

public class HomeFragment extends Fragment implements OnMealClickListener,RandomViewerInterface{

    private RecyclerView randomMealRecycler;
    private RandomMealAdapter randomMealAdapter;
    private LinearLayoutManager layoutManager;
    private RandomMealPresenterInterface randomMealPresenterInterface;



    private static final String TAG = "HomeFragment";
    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        randomMealRecycler = view.findViewById(R.id.inspirationRecycler);
        randomMealRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        randomMealRecycler.setLayoutManager(layoutManager);

        randomMealPresenterInterface = new RandomMealPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), this.getContext()));
        randomMealAdapter = new RandomMealAdapter(getContext(), this,onMealClicked);
        randomMealRecycler.setAdapter(randomMealAdapter);
        randomMealPresenterInterface.getMeals();


    }

    @Override
    public void addFavor(Meal meal) {
        randomMealPresenterInterface.addFavouriteMeal(meal);
    }

    @Override
    public void showMeals(List<Meal> MealList) {
        randomMealAdapter.setAllMeals(MealList);
        randomMealRecycler.setAdapter(randomMealAdapter);
        randomMealAdapter.notifyDataSetChanged();
    }



    private final OnMealClicked onMealClicked= new OnMealClicked() {
        @Override
        public void onMealClicked(String id) {
            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("source","home");
            startActivity(intent);
        }
    };

}