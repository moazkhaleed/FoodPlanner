package com.example.foodplanner.appNavigation.profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.example.foodplanner.models.User;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
     private EditText email;
     private EditText name;
     private EditText password;
     private Button syncBtn;
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
        name = view.findViewById(R.id.profileUsernameText);
        password = view.findViewById(R.id.profilePasswordText);
        syncBtn = view.findViewById(R.id.sync);
        logoutBtn = view.findViewById(R.id.logoutbtn);
        user = new User();
        profilePresenterInterface = new ProfilePresenter();


        user = profilePresenterInterface.getData(getContext());
        email.setText(user.getEmail().trim());
        name.setText(user.getName().trim());
        password.setText(user.getPassword().trim());

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

        Picasso.get().load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into((ImageView) view.findViewById(R.id.profile_image));
    }
}