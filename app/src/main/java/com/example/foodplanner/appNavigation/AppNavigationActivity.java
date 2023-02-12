package com.example.foodplanner.appNavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.AppNavPresenter.AppNavPresenter;
import com.example.foodplanner.auth.AuthActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class AppNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_navigation);

        AppNavPresenter presenter = new AppNavPresenter();

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

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

        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(!item.isChecked());

                switch (item.getItemId()){

                    case R.id.homeFragment:
                        Toast.makeText(getApplicationContext(),"Home Selected",Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.homeFragment);
                        break;
                    case R.id.searchFragment:
                            Toast.makeText(getApplicationContext(),"Search Selected",Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.searchFragment);
                            break;
                    case R.id.favouritesFragment:
                        if(FirebaseAuth.getInstance().getCurrentUser() != null || presenter.getLocalUserData(getApplicationContext()) != null){
                            Toast.makeText(getApplicationContext(),"Favourites Selected",Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.favouritesFragment);
                        }else{
                            Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                            showDialog();
                        }
                        break;
                    case R.id.calenderFragment:
                        if(FirebaseAuth.getInstance().getCurrentUser() != null || presenter.getLocalUserData(getApplicationContext()) != null){
                            Toast.makeText(getApplicationContext(),"Calender Selected",Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.calenderFragment);
                        }else{
                            Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                            showDialog();
                        }
                        break;
                    case R.id.profileFragment:
                        if(FirebaseAuth.getInstance().getCurrentUser() != null || presenter.getLocalUserData(getApplicationContext()) != null){
                            Toast.makeText(getApplicationContext(),"Profile Selected",Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.profileFragment);
                        }else{
                            Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                            showDialog();
                        }
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();

                }
                return true;
            }

        });
    }

    public  void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Login?");
        builder.setTitle("You Have to Login First");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}