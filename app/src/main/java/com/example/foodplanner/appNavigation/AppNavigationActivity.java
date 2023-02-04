package com.example.foodplanner.appNavigation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.foodplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_navigation);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

//        // Customize the back button
//        actionBar.setHomeAsUpIndicator(R.drawable.mybutton);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initialize Bottom Navigation View.
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);

        //Pass the ID's of Different destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.searchFragment, R.id.favouritesFragment, R.id.calenderFragment,R.id.profileFragment )
                .build();

        //Initialize NavController.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}