package com.example.foodplanner.country.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.search.view.SearchFragment;
import com.example.foodplanner.models.Category;
import com.example.foodplanner.models.Country;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CountryActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        toolbar = findViewById(R.id.countryToolbar);
        tabLayout = findViewById(R.id.countryTabLayout);
        viewPager = findViewById(R.id.countryViewPager);

        initActionBar();
        initIntent();

    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Country> countries = (List<Country>) intent.getSerializableExtra(SearchFragment.EXTRA_COUNTRY);
        int position = intent.getIntExtra(SearchFragment.EXTRA_POSITION, 0);

        ViewPagerCountryAdapter adapter = new ViewPagerCountryAdapter(
                getSupportFragmentManager(),
                countries);
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