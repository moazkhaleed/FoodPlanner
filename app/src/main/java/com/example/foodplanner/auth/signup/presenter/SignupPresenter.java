package com.example.foodplanner.auth.signup.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.auth.signup.view.SignupViewInterface;
import com.example.foodplanner.models.User;
import com.example.foodplanner.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class SignupPresenter implements SignupPresenterInterface{

    public static final String PREF_Name ="PREF";

    FirebaseAuth firebaseAuth;

    SignupViewInterface signupViewInterface;

    public SignupPresenter(SignupViewInterface signupViewInterface) {
        this.signupViewInterface = signupViewInterface;
    }

    @Override
    public void saveUserDataLocally(Context context,User user) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Gson gson = new Gson();
                String json = gson.toJson(user);
                editor.putString("user", json);
                editor.commit();

                signupViewInterface.onSuccess("");
                Toast.makeText(context,
                        "Signup Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,
                        e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
