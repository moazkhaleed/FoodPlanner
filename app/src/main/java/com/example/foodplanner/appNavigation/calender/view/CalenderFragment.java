package com.example.foodplanner.appNavigation.calender.view;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.MealDetails.view.OnMealClicked;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.calender.presenter.CalenderPresenter;
import com.example.foodplanner.appNavigation.calender.presenter.CalenderPresenterInterface;
import com.example.foodplanner.appNavigation.favourites.presenter.FavMealPresenter;
import com.example.foodplanner.appNavigation.favourites.presenter.FavMealPresenterInterface;
import com.example.foodplanner.appNavigation.favourites.view.FavMealAdapter;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;

import java.util.ArrayList;
import java.util.List;

public class CalenderFragment extends Fragment{
    private Spinner daySpinner;
    private RecyclerView scheduledMealsRecycler;
    private LinearLayoutManager layoutManager;
    private CalenderAdapter calenderAdapter;
    private CalenderPresenterInterface calenderPresenterInterface;
    private CalenderViewInterface calenderViewInterface;
    List<Meal> weekMeals,daymeals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weekMeals = new ArrayList<>();
        daymeals = new ArrayList<>();

        daySpinner = view.findViewById(R.id.daysSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);
        //daySpinner.setOnItemClickListener(this);

        scheduledMealsRecycler = view.findViewById(R.id.scheduleRecycler);
        scheduledMealsRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        scheduledMealsRecycler.setLayoutManager(layoutManager);
        calenderAdapter = new CalenderAdapter(getContext(),onMealClicked);

        calenderPresenterInterface =  new CalenderPresenter(calenderViewInterface, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));
        calenderPresenterInterface.getAllScheduledMeals().observe(getActivity(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                weekMeals = meals;
            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daymeals.clear();
                calenderAdapter.setScheduledMealsList(daymeals);
                for(int i=0; i<weekMeals.size(); i++){
                    System.out.println(weekMeals.get(i).getDate());
                    if(daySpinner.getSelectedItem().toString().equals(weekMeals.get(i).getDate())){
                        daymeals.add(weekMeals.get(i));

                    }

                }
                calenderAdapter.setScheduledMealsList(daymeals);
                calenderAdapter.notifyDataSetChanged();
                scheduledMealsRecycler.setAdapter(calenderAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }




    private final OnMealClicked onMealClicked= new OnMealClicked() {
        @Override
        public void onMealClicked(String id) {
            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("source","cal");
            startActivity(intent);
        }
    };
}