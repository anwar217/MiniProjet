package com.projet.projectfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    Button log;
    TextView createlog;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        log = findViewById(R.id.loginbtn);
        createlog = findViewById(R.id.createCount);

        fAuth = FirebaseAuth.getInstance();


       /* if (fAuth.getCurrentUser() != null) {
            updateUI(fAuth.getCurrentUser());
        }*/

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String password = pass.getText().toString();
                if (TextUtils.isEmpty(Email)) {
                    email.setError("Email is required !");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    pass.setError("Password is Required !");
                    return;
                }
                if (password.length() <= 6) {
                    pass.setError("password must be <= 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //Authentificate user
                fAuth.signInWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   // Log.d(TAG, "signInWithEmail:success");
                                   // FirebaseUser user = fAuth.getCurrentUser();
                                   // updateUI(user);
                                    Toast.makeText(LoginActivity.this, "Logged successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Error !!!!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }

                            ;

                        });
            }


        });


        createlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

}