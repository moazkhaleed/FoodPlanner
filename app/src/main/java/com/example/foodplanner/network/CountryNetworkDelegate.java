package com.example.foodplanner.network;


import com.example.foodplanner.models.Country;

import java.util.List;

public interface CountryNetworkDelegate {
    void onSuccessCountries(List<Country> countries);
    void onFailureCountries(String error);
}
