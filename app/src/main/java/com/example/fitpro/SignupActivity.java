package com.example.fitpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class SignupActivity extends AppCompatActivity {

    private StorageReference storageReference;
    private StorageTask mUploadTask;
    private FirebaseAuth firebaseAuth;
    private EditText mUserName,mUserPhone,mUserEmail,mUserPassword,mUserAge;
    private Button buttonRegister;
    private ProgressBar mProgressBar;
    private String email;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //INITIALIZING VIEWS
        mUserName = findViewById(R.id.etUserName);
        mUserPhone = findViewById(R.id.etUserPhone);
        mUserEmail = findViewById(R.id.etUserEmail);
        mUserPassword = findViewById(R.id.etUserPassword);
        mUserAge=findViewById(R.id.etUserAge);
        buttonRegister=findViewById(R.id.btnRegister);

        firebaseAuth=FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Pictures");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Users");

        //ON CLICK LISTENERS FOR BUTTONS
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CHECKING FIELDS
                email=mUserEmail.getText().toString().trim();
                String password=mUserPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mUserEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mUserPassword.setError("Password is Required.");
                    return;
                }

                if(password.length()<6){
                    mUserPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);

                //REGISTERING USER ON FIREBASE

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "SignUp Successful.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Unable to register user" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }
                });
                //UPLOADING USER DATA
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(SignupActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    //uploadData();
                }
            }
        });


    }
}