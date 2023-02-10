package com.example.foodplanner.network;



import com.example.foodplanner.models.CategoryResponse;
import com.example.foodplanner.models.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API_Service {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("lookup.php/{id}")
    Call<MealResponse> getMealDetails(
            //@Query("id") String id);
            @Path("id") String id);
            //@Query("i") String id);

    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);
    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String Area);
    @GET("search.php")
    Call<MealResponse> getMealsByName(@Query("s") String mealName);

}
