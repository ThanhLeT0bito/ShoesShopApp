package com.example.demo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    private RecyclerView recyclerview;
    private ArrayList<cartProduct> listCartProduct;
    private cartAdapter cartAdapter;
    private RecyclerView.LayoutManager layoutListCartProduct;
    TextView tvTotal;
    Double total = 0.0 ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        createListProductCart();
        cartAdapter.notifyDataSetChanged();

        tvTotal = findViewById(R.id.tvTotal);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Cart");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                total =0.0;
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    cartProduct itemProduct = postSnapshot.getValue(cartProduct.class);
                    String temp = itemProduct.getPriceProduct();
                    temp = temp.replaceAll(",","");
                    Double totalTemp = Double.parseDouble(temp) * itemProduct.getQuantityProduct();
                    total += totalTemp;
                    totalTemp=0.0;
                }
                tvTotal.setText(String.format("%.2f",total) + " VND");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        BottomNavigationView bottomNavigationView;

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_cart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.ic_user:
                        FirebaseAuth firebaseAuth;
                        FirebaseAuth.AuthStateListener mAuthListener;
                        firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user!=null){
                            startActivity(new Intent(getApplicationContext(),User.class));
                            overridePendingTransition(0,0);
                        }
                        else {
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            overridePendingTransition(0,0);
                        }
                        return true;

                    case R.id.ic_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ic_cart:
                        return true;

                    case R.id.ic_order:
                        startActivity(new Intent(getApplicationContext(),Order.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public  void createListProductCart(){
        recyclerview = findViewById(R.id.recyclerview);
        layoutListCartProduct = new LinearLayoutManager(Cart.this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutListCartProduct);
        listCartProduct = new ArrayList<cartProduct>();
        cartAdapter = new cartAdapter(Cart.this,R.layout.cart_item,listCartProduct);
        ///
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Cart");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCartProduct.removeAll(listCartProduct);

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    cartProduct itemProduct = postSnapshot.getValue(cartProduct.class);
                    listCartProduct.add(itemProduct);
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        recyclerview.setAdapter(cartAdapter);
    }
}