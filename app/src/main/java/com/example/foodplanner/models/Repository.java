package com.example.foodplanner.models;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.db.LocalSourceInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface{

    private Context context;
    RemoteSource remoteSource;
    LocalSourceInterface localSourceInterface;
    private static final String TAG = "Repository";
    private static Repository repository = null;

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

    }

    @Override
    public void removeMeal(Meal meal){

    }
    @Override
    public LiveData<List<Meal>> getMealsDB() {
        return null;
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);
    }

    @Override
    public void getMealDetails(NetworkDelegate networkDelegate, String id) {
        remoteSource.getMealDetails(networkDelegate,id);
    }
}
