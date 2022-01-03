package com.projet.projectfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {
    private RecyclerView rv;
    private DatabaseReference database;
    private CategorieAdapter categorieAdapter;

    private ArrayList<Categorie> list;

    private List<String> lastSearches = new ArrayList<>();
    BottomNavigationView navigationView;
    private MaterialSearchBar searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        navigationView=findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setHint("Search your favourite");
        searchBar.setOnSearchActionListener(MenuActivity.this);
        rv = findViewById(R.id.RC);

        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        list = new ArrayList<>();

        categorieAdapter = new CategorieAdapter(this, list);



        database = FirebaseDatabase.getInstance().getReference("Categorie");
        Log.e("Categorie", "message here");


//search
        searchBar.setSuggstionsClickListener(new SuggestionsAdapter.OnItemViewClickListener() {
            @Override
            public void OnItemClickListener(int position, View v) {
             String name = searchBar.getLastSuggestions().get(position).toString();
                Intent i = new Intent(MenuActivity.this,DescriptionActivity.class);
                Categorie c = new Categorie();
                for(Categorie cat : list){
                    if (cat.getName().equals(name)) {
                        c = cat;
                        break;
                    }
                }

                i.putExtra("categorie",c);
                startActivity(i);
            }

            @Override
            public void OnItemDeleteListener(int position, View v) {

            }
        });
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 List<String> strings = new ArrayList<>();
                for (Categorie categorie : list) {
                      if(categorie.getName().toLowerCase().contains(searchBar.getText().toLowerCase()))
                      {
                          strings.add(categorie.getName());
                      }
                }

                searchBar.updateLastSuggestions(strings);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });


//fin search
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                rv.setAdapter(categorieAdapter);

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Categorie categorie = ds.getValue(Categorie.class);
                    lastSearches.add(categorie.getName());
                    list.add(categorie);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        categorieAdapter.notifyDataSetChanged();


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                 break;
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        break;

                    case R.id.nav_location:
                        startActivity(new Intent(getApplicationContext(),MapsV.class));
                        break;

                    case R.id.nav_deconnect:
                        startActivity(new Intent(getApplicationContext(),Cart.class));

                        break;
                }

                return true;
            }
        });

    }

    private void filter(String text) {
        ArrayList<Categorie> filteredlist = new ArrayList<>();
        for (Categorie item : list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);

            }

        }
        categorieAdapter.filterlist(filteredlist);
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

}

