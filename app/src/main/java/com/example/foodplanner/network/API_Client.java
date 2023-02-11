package com.example.foodplanner.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner.models.CategoryResponse;
import com.example.foodplanner.models.CountryResponse;
import com.example.foodplanner.models.Ingredient;
import com.example.foodplanner.models.IngredientResponse;
import com.example.foodplanner.models.MealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client implements RemoteSource{

    static API_Service api_service;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "API_Client";
    private static API_Client api_client = null;

    private API_Client() {
    }

    public synchronized static API_Client getInstance() {
        if (api_client == null) {
            api_client = new API_Client();
            Gson gson = new GsonBuilder().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            api_service = retrofit.create(API_Service.class);
        }
        return api_client;
    }
    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        Call<MealResponse> meals = api_service.getRandomMeal();
        Log.d(TAG, "startCall: ");

        meals.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    networkDelegate.onSuccess(response.body().getMeals());

                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                networkDelegate.onFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getMealDetails(NetworkDelegate networkDelegate, String id) {

        Call<MealResponse> mealDetails = api_service.getMealDetails(id);
        Log.d(TAG, "startCall: ");

        mealDetails.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    networkDelegate.onSuccess(response.body().getMeals());

                }
            }

            @Override
            public void onFailure(@NonNull Call<MealResponse> call, @NonNull Throwable t) {
                networkDelegate.onFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getCategories(CategoryNetworkDelegate networkDelegate) {
        Call<CategoryResponse> categories = api_service.getCategories();
        Log.d(TAG, "getCategories startCall: ");

        categories.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryResponse> call, @NonNull Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "getCategories onResponse: " + response.body().getCategories());
                    // TODO Multiple Success Response
                    networkDelegate.onSuccessCategory(response.body().getCategories());

                }
            }

            @Override
            public void onFailure(@NonNull Call<CategoryResponse> call, @NonNull Throwable t) {
                networkDelegate.onFailureCategory(t.getMessage());
            }
        });
    }

    @Override
    public void getIngredients(IngredientNetworkDelegate networkDelegate) {
        Single<IngredientResponse> ingredients = api_service.getIngredients("list");
        Log.d(TAG, "getIngredients startCall: ");
        ingredients.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->networkDelegate.onSuccessIngredients(item.getMeals()),
                        e->networkDelegate.onFailureIngredients(e.getMessage())
                );
    }

    @Override
    public void getCountries(CountryNetworkDelegate networkDelegate) {
        Single<CountryResponse> countries = api_service.getCountries("list");
        Log.d(TAG, "getCountries startCall: ");
        countries.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->networkDelegate.onSuccessCountries(item.getMeals()),
                        e->networkDelegate.onFailureCountries(e.getMessage())
                );


    }

    @Override
    public void searchByName(NetworkDelegate networkDelegate, String name) {
        Call<MealResponse> meals = api_service.getMealsByName(name);
        Log.d(TAG, "searchByName startCall: ");
        meals.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealResponse> call, @NonNull Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "searchByName onResponse: " + response.body());
                    networkDelegate.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MealResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "searchByName onFailure: " + t.getLocalizedMessage());
                networkDelegate.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void searchByCategory(NetworkDelegate networkDelegate, String category) {
        Call<MealResponse> meals = api_service.getMealsByCategory(category);
        Log.d(TAG, "startCall: ");
        meals.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(@NonNull Call<MealResponse> call, @NonNull Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    networkDelegate.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MealResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                networkDelegate.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void searchByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        Call<MealResponse> meals = api_service.getMealsByIngredient(ingredient);
        Log.d(TAG, "startCall: ");
        meals.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(@NonNull Call<MealResponse> call, @NonNull Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    networkDelegate.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MealResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                networkDelegate.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void searchByCountry(NetworkDelegate networkDelegate, String country) {
        Call<MealResponse> meals = api_service.getMealsByCountry(country);
        Log.d(TAG, "startCall: ");
        meals.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(@NonNull Call<MealResponse> call, @NonNull Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    networkDelegate.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MealResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                networkDelegate.onFailure(t.getMessage());
            }
        });
    }
}
