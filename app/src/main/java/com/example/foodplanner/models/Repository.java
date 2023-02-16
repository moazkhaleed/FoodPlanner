package com.example.foodplanner.models;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.db.LocalSourceInterface;
import com.example.foodplanner.network.CategoryNetworkDelegate;
import com.example.foodplanner.network.CountryNetworkDelegate;
import com.example.foodplanner.network.IngredientNetworkDelegate;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.RemoteSource;
import com.example.foodplanner.network.TrendingNetworkDelegate;
import com.example.foodplanner.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface{

    private Context context;
    RemoteSource remoteSource;
    LocalSourceInterface localSourceInterface;
    private static final String TAG = "Repository";
    private static Repository repository = null;

    FirebaseDatabase database;
    DatabaseReference reference;

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSourceInterface, Context context) {
        if (repository == null)
            repository = new Repository(remoteSource, localSourceInterface, context);
           //repository = new Repository(remoteSource, new AppDataBase(), context);
        return repository;

    }

    private Repository(RemoteSource remoteSource, LocalSourceInterface localSourceInterface, Context context) {
        this.remoteSource = remoteSource;
        this.localSourceInterface = localSourceInterface;
        this.context = context;

    }

    @Override
    public void insertMeal(Meal meal) {

        localSourceInterface.insertMeal(meal);

    }

    @Override
    public void removeMeal(Meal meal){
        localSourceInterface.removeMeal(meal);

    }
    @Override
    public LiveData<List<Meal>> getMealsDB() {

        return localSourceInterface.getAllMealsStored();
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);
    }

    @Override
    public void getMealDetails(NetworkDelegate networkDelegate, String id) {
        remoteSource.getMealDetails(networkDelegate,id);
    }

    @Override
    public LiveData<List<Meal>> getStoredDetails(String id) {
        return localSourceInterface.getDetailsMealStored(id);
    }


    @Override
    public void getCategories(CategoryNetworkDelegate networkDelegate) {
        remoteSource.getCategories(networkDelegate);
    }

    @Override
    public void getIngredients(IngredientNetworkDelegate networkDelegate) {
        remoteSource.getIngredients(networkDelegate);
    }

    @Override
    public void getCountries(CountryNetworkDelegate networkDelegate) {
        remoteSource.getCountries(networkDelegate);
    }

    @Override
    public void getMealsByName(TrendingNetworkDelegate networkDelegate, String name) {
        remoteSource.searchByName(networkDelegate,name);
    }

    @Override
    public void getMealsByCategory(NetworkDelegate networkDelegate, String category) {
        remoteSource.searchByCategory(networkDelegate,category);
    }

    @Override
    public void getMealsByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        remoteSource.searchByIngredient(networkDelegate,ingredient);
    }

    @Override
    public void getMealsByCountry(NetworkDelegate networkDelegate, String country) {
        remoteSource.searchByCountry(networkDelegate,country);
    }

    @Override
    public LiveData<List<Meal>> getScheduledMeas() {
        return localSourceInterface.getAllScheduledMeals();
    }

    @Override
    public LiveData<List<Meal>> getFavMeas() {
        return localSourceInterface.getAllFavMeals();
    }

    @Override
    public void addMealsToFirebase(String id,List<Meal> meals) {
        System.out.println("addMealsToFirebase");
        System.out.println(meals.get(0).getStrMeal());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        reference.child(Constants.USERS).child(id).child(Constants.MEALS).setValue(meals);
    }

    @Override
    public void getMealsFromFirebase(String id) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        final ArrayList<Meal> meals = new ArrayList<>();

        reference.child(Constants.USERS).child(id).child(Constants.MEALS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot postSnapshot: task.getResult().getChildren()) {
                        meals.add(postSnapshot.getValue(Meal.class));

                        System.out.println("getMealsFirebase");
                        System.out.println(meals.size());
                    }
                    if(!meals.isEmpty()){
                        System.out.println("getMealsFirebase");
                        System.out.println(meals.size());
                        localSourceInterface.clearMeals();
                        for(Meal meal : meals){
                            localSourceInterface.insertMeal(meal);
                        }
                    }

                }
            }
        });
    }

}
