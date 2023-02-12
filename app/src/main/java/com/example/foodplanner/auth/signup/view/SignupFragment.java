package com.example.foodplanner.auth.signup.view;

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
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.AppNavigationActivity;
import com.example.foodplanner.auth.signup.presenter.SignupPresenter;
import com.example.foodplanner.models.User;

import java.util.regex.Pattern;


public class SignupFragment extends Fragment implements SignupViewInterface{

    private Button signupBtn;
    private EditText nametxt;
    private EditText emailtxt;
    private EditText passwordtxt;
    private EditText confirmPasswordtxt;

    SignupPresenter signupPresenter;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signupPresenter = new SignupPresenter(this);

        nametxt = view.findViewById(R.id.name_signup_txtField);
        emailtxt = view.findViewById(R.id.email_signup_txtField);
        passwordtxt = view.findViewById(R.id.password_signup_txtField);
        confirmPasswordtxt = view.findViewById(R.id.confirmPass_signup_txtField);
        signupBtn = view.findViewById(R.id.SignupToHomeBtn);
        user = new User();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(checkRegex(nametxt.getText().toString().trim(),emailtxt.getText().toString().trim()
                         ,passwordtxt.getText().toString().trim(),confirmPasswordtxt.getText().toString().trim())){
                     user.setEmail(emailtxt.getText().toString());
                     user.setPassword(passwordtxt.getText().toString());
                     user.setName(nametxt.getText().toString());
                     signupPresenter.saveUserDataLocally(getContext(),user);
                 }

            }
        });
    }

    private boolean checkRegex(String name,String email, String pass, String confirmPass){
        if (name.isEmpty() ||  email.isEmpty() || pass.isEmpty()||confirmPass.isEmpty()){
            Toast.makeText(getActivity(), "Required Empty Fields", Toast.LENGTH_SHORT).show();
        }else{
            if(Pattern.matches("^[a-zA-Z][a-zA-Z0-9_]{3,29}$",name)){
                if(Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",email)){
                    if(Pattern.matches("[a-zA-Z0-9]{8,20}$",pass)){
                        if(confirmPass.equals(pass) ){
                            return true;
                        }else{
                            Toast.makeText(getActivity(), "Password didn't match!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "choose strong pass!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Invalid mail!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getActivity(), "Invalid Name!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    @Override
    public void onSuccess(String message) {
        Intent intent = new Intent(getActivity(), AppNavigationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onError(String message) {

    }
}