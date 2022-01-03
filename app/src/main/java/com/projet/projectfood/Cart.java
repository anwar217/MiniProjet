package com.projet.projectfood;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        managementCart = new ManagementCart(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Facture");
        initView();
        initList();
        bottomNavigation();
        calculateCard();
    }
    private void bottomNavigation() { }
   private void initView() {
       totalFeeTxt = findViewById(R.id.totalFeeTxt);
       taxTxt = findViewById(R.id.taxTxt);
       deliveryTxt = findViewById(R.id.deliveryTxt);
       totalTxt = findViewById(R.id.totalTxt);
       recyclerViewList = findViewById(R.id.view);
       scrollView = findViewById(R.id.scrollView);
       emptyTxt = findViewById(R.id.emptyTxt);
   }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), managementCart, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;
        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }

    public void checkout(View view) {
        Facture facture = new Facture();
        List<CategorieByFacture> categorieByFactures = new ArrayList<>();
        double percentTax = 0.02;  //you can change this item for tax price
        double delivery = 10;     //you can change this item you need price for delivery
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        for (Categorie categorie : managementCart.getListCart()){
            CategorieByFacture categorieByFacture = new CategorieByFacture();
            categorieByFacture.setName(categorie.getName());
            categorieByFacture.setNumberOfItems(categorie.getNumberInCart());
            categorieByFactures.add(categorieByFacture);
        }
        facture.setCategorieByFacture(categorieByFactures);
        facture.setTax(tax);
        facture.setTotalPrice(total);
        facture.setTotalPriceHt(itemTotal);
        String id = databaseReference.push().getKey();
        facture.setId(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.child(id).setValue(facture);
                Toast.makeText(Cart.this, "data added", Toast.LENGTH_SHORT).show();
                managementCart.clearData();
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cart.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}