package com.example.foodplanner.network;

public interface RemoteSource {
    void enqueueCall(NetworkDelegate networkDelegate);
    void getMealDetails(NetworkDelegate networkDelegate, String id);
    void getCategories(CategoryNetworkDelegate networkDelegate);
    void searchByName(NetworkDelegate networkDelegate, String name);
    void searchByCategory(NetworkDelegate networkDelegate, String category);
    void searchByIngredient(NetworkDelegate networkDelegate, String ingredient);
    void searchByCountry(NetworkDelegate networkDelegate, String country);
}
