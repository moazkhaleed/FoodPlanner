package com.example.foodplanner.appNavigation.profile.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.appNavigation.profile.view.ProfileViewerInterface;
import com.example.foodplanner.auth.signup.presenter.SignupPresenter;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.RepositoryInterface;
import com.example.foodplanner.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.List;

public class ProfilePresenter implements ProfilePresenterInterface{
    User user = new User();

    ProfileViewerInterface viewerInterface;

    private RepositoryInterface repositoryInterface;



    public ProfilePresenter(ProfileViewerInterface viewerInterface,RepositoryInterface repositoryInterface) {
        this.viewerInterface = viewerInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public User getData(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("user", null);
        user = gson.fromJson(json, User.class);

        return user;
    }

    @Override
    public void deleteData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeals() {
        return repositoryInterface.getMealsDB();
    }

    @Override
    public void addMealsToFirebase(List<Meal> meals){
        repositoryInterface.addMealsToFirebase(FirebaseAuth.getInstance().getUid(),meals);
    }

    @Override
    public void getMealsFirebase(){
        repositoryInterface.getMealsFromFirebase(FirebaseAuth.getInstance().getUid());
    }



}
