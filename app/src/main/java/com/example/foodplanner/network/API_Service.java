package com.example.foodplanner.network;



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


}
