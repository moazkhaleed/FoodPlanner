package com.example.foodplanner.auth.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.auth.login.view.LoginViewInterface;
import com.example.foodplanner.auth.signup.presenter.SignupPresenter;
import com.example.foodplanner.models.User;
import com.example.foodplanner.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

public class LoginPresenter implements LogInPresenterInterface{
    FirebaseAuth firebaseAuth;
    LoginViewInterface loginViewInterface;

    public LoginPresenter(LoginViewInterface loginViewInterface) {
        this.loginViewInterface = loginViewInterface;
    }

    @Override
    public void login(Context context,User user) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                firebaseUser.getEmail();
                Utils.showDialogMessage(context, "onSuccess ", firebaseUser.getEmail());

                Toast.makeText(context, "onSuccess"+ firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = context.getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Gson gson = new Gson();
                String json = gson.toJson(user);
                editor.putString("user", json);
                editor.commit();
                loginViewInterface.onSuccess("hello");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Utils.showDialogMessage(context, "Error ", "");
            }
        });



    }


}
