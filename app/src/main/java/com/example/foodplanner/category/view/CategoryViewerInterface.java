package com.example.foodplanner.category.view;
import com.example.foodplanner.models.Meal;
import java.util.List;

public interface CategoryViewerInterface {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meal> meals);
    void onErrorLoading(String message);
}
