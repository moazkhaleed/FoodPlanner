package com.example.foodplanner.ingredient.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.search.view.SearchFragment;

import com.example.foodplanner.models.Ingredient;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class IngredientActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        toolbar = findViewById(R.id.ingredientToolbar);
        tabLayout = findViewById(R.id.ingredientTabLayout);
        viewPager = findViewById(R.id.ingredientViewPager);

        initActionBar();
        initIntent();

    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Ingredient> ingredients =(List<Ingredient>) intent.getSerializableExtra(SearchFragment.EXTRA_INGREDIENT);
        int position = intent.getIntExtra(SearchFragment.EXTRA_POSITION, 0);

        ViewPagerIngredientAdapter adapter = new ViewPagerIngredientAdapter(
                getSupportFragmentManager(),
                ingredients);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        adapter.notifyDataSetChanged();

    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}