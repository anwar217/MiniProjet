package com.projet.projectfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  start=findViewById(R.id.start);

  start.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          startActivity(new Intent(getApplicationContext(),LoginActivity.class));
      }
  });


    }


}