package com.example.foodplanner.ingredient.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foodplanner.models.Ingredient;

import java.util.List;

public class ViewPagerIngredientAdapter extends FragmentPagerAdapter {

    private List<Ingredient> ingredients;

    public ViewPagerIngredientAdapter(FragmentManager fm, List<Ingredient> ingredients) {
        super(fm);
        this.ingredients = ingredients;
    }

    @Override
    public Fragment getItem(int i) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putString("EXTRA_DATA_NAME", ingredients.get(i).getStrIngredient());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ingredients.get(position).getStrIngredient();
    }
}
