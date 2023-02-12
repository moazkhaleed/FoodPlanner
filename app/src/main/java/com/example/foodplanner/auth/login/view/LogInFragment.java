package com.example.foodplanner.auth.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.AppNavigationActivity;
import com.example.foodplanner.auth.AuthActivity;
import com.example.foodplanner.auth.login.presenter.LoginPresenter;
import com.example.foodplanner.auth.signup.presenter.SignupPresenter;
import com.example.foodplanner.models.User;
import com.example.foodplanner.utils.Utils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;


public class LogInFragment extends Fragment implements LoginViewInterface{

    private static final int RC_SIGN_IN = 123;
    private Button loginBtn;
    private EditText email;
    private EditText password;
    LoginPresenter loginPresenter;
    Toast toast;
    User user;

    GoogleSignInClient mGoogleSignInClient;
    private Button googleLoginBtn;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        createRequest();
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
        googleLoginBtn = view.findViewById(R.id.googleLoginBtn);
        email = view.findViewById(R.id.email_login_txtField);
        password = view.findViewById(R.id.password_login_txtField);

        loginPresenter = new LoginPresenter(this);
        user = new User();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput(email.getText().toString(),password.getText().toString())){
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    loginPresenter.login(getContext(),user);
//                    onSuccess("hello");
                }else{
                    toast = Toast.makeText(getContext(),
                            "Required Empty Fields",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


    }

    private GoogleSignInOptions createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("177919987543-6c9ieorukrill38grdodqi3p1jubd2m4.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this.getActivity(), gso);
        return gso;
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                System.out.println("Google Error"+e.toString());
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user != null){
                                User userData = new User();

                                userData.setEmail(user.getEmail());
                                userData.setPassword(user.getEmail());

                                Intent i = new Intent(getActivity(), AppNavigationActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                Toast.makeText(getContext(), user.getEmail(), Toast.LENGTH_SHORT).show();

                                SharedPreferences sharedPreferences = getContext().getSharedPreferences(SignupPresenter.PREF_Name, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                Gson gson = new Gson();
                                String json = gson.toJson(userData);
                                editor.putString("user", json);
                                editor.commit();
                            }

                        } else {

                            Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();

                        }
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
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

    private boolean validateInput(String email, String Password){
        if(email.isEmpty() || Password.isEmpty())
           return false;
        return true;
    }
}