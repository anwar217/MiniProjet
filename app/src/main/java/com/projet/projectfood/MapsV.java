package com.projet.projectfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MapsV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_v);
        Fragment fragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.map, fragment).commit();
    }
}