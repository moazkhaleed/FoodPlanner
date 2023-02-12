package com.example.foodplanner.MealDetails.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.MealDetails.presenter.MealDetailsPresenter;
import com.example.foodplanner.MealDetails.presenter.MealDetailsPresenterInterface;
import com.example.foodplanner.R;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.network.RemoteSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsViewerInterface {

    private static final String TAG ="MealDetailsActivity";
    private final String API_KEY = "AIzaSyCE4TPrEFoEnfLsgoJdgGuJbqYiJHPNEgI";
    private String id;
    private String source;
    private TextView mealName;
    private TextView mealCountry;
    private TextView mealDescription;
    private RecyclerView ingredientsRecycler;
    private RecyclerView stepsRecycler;
    private ImageView mealImage;
    private ImageButton addToScheduleBtn;
    private ImageButton addToFavBtn;
    private YouTubePlayerView mealVideo;
    private MealDetailsPresenterInterface mealDetailsPresenterInterface;
    private List<String> mealIngredients;
    private List<String> mealMeasures;
    private String[] mealSteps;
    private List<String> values;
    private LinearLayoutManager layoutManager;
    private LinearLayoutManager inLayoutManager;
    private StepsAdapter adapter;
    private IngredientsAdapter ingredientsAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        mealIngredients = new ArrayList<>();
        mealMeasures = new ArrayList<>();
        values = new ArrayList<>();
        id = getIntent().getStringExtra("id");
        source = getIntent().getStringExtra("source");

        init();

        stepsRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        stepsRecycler.setLayoutManager(layoutManager);


        ingredientsRecycler.setHasFixedSize(true);
        inLayoutManager = new LinearLayoutManager(this);
        inLayoutManager.setOrientation(RecyclerView.VERTICAL);
        ingredientsRecycler.setLayoutManager(inLayoutManager);

        mealDetailsPresenterInterface = new MealDetailsPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getApplicationContext()), getApplicationContext()), id);

        if(source.equals("fav")){
            mealDetailsPresenterInterface.getDetailsFromRoom().observe(this, new Observer<List<Meal>>() {
                @Override
                public void onChanged(List<Meal> meals) {
                    viewDetails(meals);
                }
            });

        }else{
            mealDetailsPresenterInterface.getDetails();

        }
    }


    private void init() {
        mealName = findViewById(R.id.mealName);
        mealCountry = findViewById(R.id.mealCountry);
        mealDescription = findViewById(R.id.mealDescription);
        mealImage = findViewById(R.id.mealImg);
        mealVideo = findViewById(R.id.mealVideo);
        ingredientsRecycler = findViewById(R.id.ingredientsRecyclerView);
        stepsRecycler = findViewById(R.id.stepsRecycler);
        addToScheduleBtn = findViewById(R.id.addMealBtn);
        addToFavBtn = findViewById(R.id.addFavBtnn);

    }

    @Override
    public void showDetails(List<Meal> MealList) {
        getIngredients(MealList.get(0));
        setUI(MealList);
    }

    private void getIngredients(Meal meal){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(meal); // obj is your object
        Map<String,String> result = new Gson().fromJson(json,Map.class);

        for(int i=1; i<=20; i++){
            if(result.get("strIngredient" + i) == null || result.get("strIngredient"+i).isEmpty())
                break;
            mealIngredients.add(result.get("strIngredient"+i));
            mealMeasures.add(result.get("strMeasure"+i));
        }
    }

    public List<String> zipped(List<String> Ingredients, List<String> Measures){

        List<String> val= new ArrayList<>();
        Observable<String> obsIng = Observable.fromArray(Ingredients.toArray(new String [Ingredients.size()]));
        Observable<String> obsMes = Observable.fromArray(Measures.toArray(new String [Measures.size()]));


        obsMes.subscribeOn(Schedulers.io())
                .zipWith(obsIng,(Ingredient,Measure) ->
                        Ingredient.concat(" ")
                                .concat(Measure)
                                .concat(" ")
                ).subscribe(
                        item -> {val.add(item);
                            Log.i(TAG, "IngredientsAdapter: "+item);
                        }
                );
        return val;
    }

    public void viewDetails(List<Meal> MealList) {
        getIngredients(MealList.get(0));
        setUI(MealList);
    }

    public void displayVideo(String path) {
        String uri =path;
        String videoId = uri.substring(uri.lastIndexOf('=')+1);
        mealVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId,0);
                        //loadVideo(videoId, 0);
            }
        });

    }
    private void setUI(List<Meal> MealList){
        Meal meal = MealList.get(0);
        mealSteps = meal.getStrInstructions().split("[.]");
        adapter = new StepsAdapter(this, mealSteps);
        stepsRecycler.setAdapter(adapter);
        mealName.setText(meal.getStrMeal());
        mealCountry.setText(meal.getStrArea());
        mealDescription.setText(meal.getStrCategory());
        Glide.with(getApplicationContext()).load(meal.strMealThumb)
                .apply(new RequestOptions())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(mealImage);
        displayVideo(meal.getStrYoutube());

        ingredientsAdapter = new IngredientsAdapter(this,zipped(mealIngredients,mealMeasures));
        ingredientsRecycler.setAdapter(ingredientsAdapter);

        addToScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTime(meal);
            }
        });

        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal.setFav(true);
                mealDetailsPresenterInterface.addFavouriteMeal(meal);
            }
        });
    }

    private void pickDateTime(Meal meal){
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.set(year, month, dayOfMonth);
                meal.setDate(getDayName(date.getTime().getDay()));
                mealDetailsPresenterInterface.addToSchedule(meal);
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }

    private String getDayName(int n){
        String day="";
        switch (n){
            case 0:
                day="Sunday";
                break;
            case 1:
                day="Monday";
                break;
            case 2:
                day="Tuesday";
                break;
            case 3:
                day="Wednesday";
                break;
            case 4:
                day="Thursday";
                break;
            case 5:
                day="Friday";
                break;
            case 6:
                day="Saturday";
                break;
        }
        return day;
    }



}