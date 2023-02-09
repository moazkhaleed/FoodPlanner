package com.example.foodplanner.MealDeails.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.MealDeails.presenter.MealDetailsPresenter;
import com.example.foodplanner.MealDeails.presenter.MealDetailsPresenterInterface;
import com.example.foodplanner.R;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.network.API_Client;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsViewerInterface {

    String id;
    TextView mealName;
    TextView mealCountry;
    TextView mealDescription;
    RecyclerView ingredientsRecycler;
    RecyclerView stepsRecycler;
    ImageView mealImage;
    VideoView mealVideo;
    MealDetailsPresenterInterface mealDetailsPresenterInterface;
    List<Meal> mealdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        mealdetails = new ArrayList<>();

        id = getIntent().getStringExtra("id");
        init();

        mealDetailsPresenterInterface = new MealDetailsPresenter(this,Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getApplicationContext()),getApplicationContext()),id);
        mealDetailsPresenterInterface.getDetails();
    }



    private void init(){
        mealName = findViewById(R.id.mealName);
        mealCountry = findViewById(R.id.mealCountry);
        mealDescription = findViewById(R.id.mealDescription);
        mealImage = findViewById(R.id.mealImg);
        mealVideo = findViewById(R.id.mealVideo);
        ingredientsRecycler = findViewById(R.id.ingredientsRecyclerView);
        stepsRecycler = findViewById(R.id.stepsRecycler);

    }

    @Override
    public void showDetails(List<Meal> MealList) {
        mealName.setText(MealList.get(0).getStrMeal());
        mealCountry.setText(MealList.get(0).getStrArea());
        mealDescription.setText(MealList.get(0).getStrCategory());
        Glide.with(getApplicationContext()).load(MealList.get(0).strMealThumb)
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(mealImage);
    }



}