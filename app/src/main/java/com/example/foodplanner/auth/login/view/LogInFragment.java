package com.example.foodplanner.auth.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.AppNavigationActivity;
import com.example.foodplanner.auth.login.presenter.LoginPresenter;
import com.example.foodplanner.models.User;


public class LogInFragment extends Fragment implements LoginViewInterface{

    private Button loginBtn;
    private EditText email;
    private EditText password;
    LoginPresenter loginPresenter;
    Toast toast;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_log_in, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginBtn = view.findViewById(R.id.logToHomeBtn);
        email = view.findViewById(R.id.email_login_txtField);
        password = view.findViewById(R.id.password_login_txtField);
        loginPresenter = new LoginPresenter();
        user = new User();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput(email.getText().toString(),password.getText().toString())){
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    loginPresenter.login(getContext(),user);
                    onSuccess("hello");
                }

                else{
                    toast = Toast.makeText(getContext(),
                            "Required Empty Fields",
                            Toast.LENGTH_SHORT);
                toast.show();}
            }
        });
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

    private boolean validateInput(String email, String Password){
        if(email.isEmpty() || Password.isEmpty())
           return false;
        return true;
    }
}