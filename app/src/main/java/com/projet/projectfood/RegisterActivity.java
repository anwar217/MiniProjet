package com.projet.projectfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class RegisterActivity extends AppCompatActivity {
    EditText nom,email,pass,phone;
    Button rgb;
    TextView login;
    FirebaseAuth fAuth;
    ProgressBar prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nom=findViewById(R.id.FullName);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        login=findViewById(R.id.already);
        rgb=findViewById(R.id.register);
        prog=findViewById(R.id.progressBar);

        fAuth=FirebaseAuth.getInstance();


        rgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString().trim();
                String password=pass.getText().toString();
                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is required !");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Password is Required !");
                }
                if(password.length()<=6){
                    pass.setError("password must be <= 6 Characters");
                }
                prog.setVisibility(View.VISIBLE);
                //register user in fire base
                fAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Error !!!!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}