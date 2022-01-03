package com.projet.projectfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



    public class ProfileActivity extends AppCompatActivity {
        private TextView mobile,mail;
        private ImageView tel,email,facebook,instagram,whatsup;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            tel=findViewById(R.id.tel);
            email=findViewById(R.id.email);
            mobile=findViewById(R.id.mobile);
            mail=findViewById(R.id.mail);
           facebook=findViewById(R.id.facebook);
           instagram=findViewById(R.id.instagram);
            whatsup=findViewById(R.id.whatsup);
            Intent  intent = new Intent();

            intent.putExtra("tel",mobile.getText().toString());


            tel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri numberphone=Uri.parse("tel:"+mobile.getText().toString());
                    Intent i=new Intent(Intent.ACTION_DIAL,numberphone);
                    startActivity(i);

                }
            });
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmail.com"+mail.getText().toString()));
                    startActivity(j);

                }
            });
            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/moslima2015"));
                    startActivity(j);

                }
            });

           instagram.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/anwar_bensouissi"));
                   startActivity(j);
               }
           });
           whatsup.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                       Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.whatsApp"+51273264));
                       startActivity(j);
               }
           });


        }


    }
