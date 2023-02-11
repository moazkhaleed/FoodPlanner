package com.example.foodplanner.models;

import java.io.Serializable;

public class Country implements Serializable {
    public String strArea;

    public Country() {
    }

    public Country(String strArea) {
        this.strArea = strArea;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
