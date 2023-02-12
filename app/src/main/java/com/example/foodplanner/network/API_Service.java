package com.example.foodplanner.network;



import com.example.foodplanner.models.CategoryResponse;
import com.example.foodplanner.models.CountryResponse;
import com.example.foodplanner.models.Ingredient;
import com.example.foodplanner.models.IngredientResponse;
import com.example.foodplanner.models.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API_Service {
    @GET("random.php")
    Single<MealResponse> getRandomMeal();
    @GET("lookup.php")
    Single<MealResponse> getMealDetails(@Query("i") String id);
    @GET("categories.php")
    Single<CategoryResponse> getCategories();
    @GET("list.php")
    Single<IngredientResponse> getIngredients(@Query("i") String list);
    @GET("list.php")
    Single<CountryResponse> getCountries(@Query("a") String list);
    @GET("filter.php")
    Single<MealResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Single<MealResponse> getMealsByIngredient(@Query("i") String ingredient);
    @GET("filter.php")
    Single<MealResponse> getMealsByCountry(@Query("a") String Area);
    @GET("search.php")
    Single<MealResponse> getMealsByName(@Query("s") String mealName);

}
