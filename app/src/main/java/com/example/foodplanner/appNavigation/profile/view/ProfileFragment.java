package com.example.foodplanner.appNavigation.profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.profile.presenter.ProfilePresenter;
import com.example.foodplanner.appNavigation.profile.presenter.ProfilePresenterInterface;
import com.example.foodplanner.auth.AuthActivity;
import com.example.foodplanner.db.LocalSource;
import com.example.foodplanner.models.Meal;
import com.example.foodplanner.models.Repository;
import com.example.foodplanner.models.User;
import com.example.foodplanner.network.API_Client;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileFragment extends Fragment implements ProfileViewerInterface{
     private EditText email;
     private EditText name;
     private EditText password;
     private Button syncBtn;
     private Button downloadBtn;
     private ImageButton logoutBtn;
     private ProfilePresenterInterface profilePresenterInterface;
     private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.emailEditText);
        //Todo set visibility
        name = view.findViewById(R.id.profileUsernameText);
        password = view.findViewById(R.id.profilePasswordText);
        syncBtn = view.findViewById(R.id.sync);
        downloadBtn = view.findViewById(R.id.download);
        logoutBtn = view.findViewById(R.id.logoutbtn);
        user = new User();
        profilePresenterInterface = new ProfilePresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));

        user = profilePresenterInterface.getData(getContext());
        if(user != null){
            email.setText(user.getEmail().trim());
//            name.setText(user.getName().trim());
            name.setText("guest");
            password.setText(user.getPassword().trim());

        }

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePresenterInterface.deleteData(getContext());
                Intent i = new Intent(getActivity(), AuthActivity.class);
              //  i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePresenterInterface.getAllFavMeals().observe(getActivity(), new Observer<List<Meal>>() {
                    @Override
                    public void onChanged(List<Meal> meals) {
                        profilePresenterInterface.addMealsToFirebase(meals);
                    }
                });
            }
        });

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePresenterInterface.getMealsFirebase();
            }
        });


        Picasso.get().load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into((ImageView) view.findViewById(R.id.profile_image));
    }
}