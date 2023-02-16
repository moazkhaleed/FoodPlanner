package com.example.foodplanner.appNavigation.search.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.MealDetails.view.OnMealClicked;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;

public class SearchByNameActivity extends AppCompatActivity  {
    private List<Meal> allMealsByName;
    private List<Meal> filteredList;
    private EditText searchTxt;
    private RecyclerView searchByNameRecycler;
    private SearchByNameAdapter adapter;
    private Observable<Meal> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        allMealsByName = new ArrayList<>();
        filteredList = new ArrayList<>();
        allMealsByName = (List<Meal>) getIntent().getSerializableExtra("mealsList");

        searchTxt = findViewById(R.id.searchTxt);
        searchByNameRecycler = findViewById(R.id.searchRecycler);

        searchByNameRecycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        searchByNameRecycler.setLayoutManager(manager);
        adapter = new SearchByNameAdapter(this,onMealClicked);

      observable = Observable.fromArray(allMealsByName.toArray(new Meal[allMealsByName.size()]));



        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filteredList = allMealsByName.stream()
                                .filter(element-> element.getStrMeal().toLowerCase().contains(s.toString().toLowerCase())).
                        collect(Collectors.toList());


                adapter.setAllMeals(filteredList);
                searchByNameRecycler.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
                 
            }
        });
    }

    private final OnMealClicked onMealClicked= new OnMealClicked() {
        @Override
        public void onMealClicked(String id) {
            Toast.makeText(SearchByNameActivity.this, id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SearchByNameActivity.this, MealDetailsActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("source","searchByName");
            startActivity(intent);
        }
    };
}