package com.example.fitpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitpro.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginButton, signupButton;
    private Button loginForgotPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        findViewById();
        firebaseAuth = FirebaseAuth.getInstance();
        onClickListener();
    }

    private void findViewById()
    {
        loginEmail = findViewById(R.id.etUserEmail);
        loginPassword = findViewById(R.id.etUserPassword);
        loginButton = findViewById(R.id.btnLogIn);
        loginForgotPassword = findViewById(R.id.btnForgotPasssword);
        signupButton=findViewById(R.id.btnRegister);

        loginEmail.setText("");
        loginPassword.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();

        loginEmail.setText("");
        loginPassword.setText("");
    }

    private void onClickListener()
    {

        loginForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                if(checkEmpty())
                {
                    loginButton.setEnabled(false);
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Log.d("Login SUccess", "abcd");
                                Intent i= new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);

                            }
                            else
                            {
                                loginButton.setEnabled(true);
                                Snackbar.make(findViewById(android.R.id.content),"Unable To Login!!!",Snackbar.LENGTH_SHORT).setTextColor(getColor(R.color.colorPrimary)).setBackgroundTint(getColor(R.color.White)).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            loginButton.setEnabled(true);
                            Snackbar.make(findViewById(android.R.id.content),"Unable To Login!!!",Snackbar.LENGTH_SHORT).setTextColor(getColor(R.color.colorPrimary)).setBackgroundTint(getColor(R.color.White)).show();
                        }
                    });
                }
            }
        });
    }

    public Boolean checkEmpty()
    {
        if(loginEmail.getText().length()==0)
        {
            loginEmail.setError("Please enter your email");
            loginEmail.requestFocus();
            return false;
        }
        else if(loginPassword.getText().length()==0)
        {
            loginPassword.setError("Please enter your password");
            loginPassword.requestFocus();
            return false;
        }
        return true;
    }



}