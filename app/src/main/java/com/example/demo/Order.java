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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Order extends AppCompatActivity {

    RecyclerView rcViewItemListOrder;
    RecyclerView.LayoutManager layoutItemListOrder;
    ArrayList<orderProduct> oderList;
    orderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ///


        rcViewItemListOrder = findViewById(R.id.order_rcv);
        layoutItemListOrder =  new LinearLayoutManager(this);
        rcViewItemListOrder.setLayoutManager(layoutItemListOrder);
        oderList = new ArrayList<>();
        orderAdapter = new orderAdapter(Order.this,R.layout.order_item,oderList);

//        orderProduct temp = new orderProduct("id01","namePro",40,"vang",2);
//        orderProduct temp1 = new orderProduct("id02","namePro2",40,"Trắng",3);
//        oderList.add(temp);
//        oderList.add(temp1);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Cart");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                oderList.removeAll(oderList);

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    cartProduct itemProduct = postSnapshot.getValue(cartProduct.class);
                    orderProduct tempProduct = new orderProduct(itemProduct.getIdProduct(),itemProduct.getNameProduct(),itemProduct.getPriceProduct(),
                            itemProduct.getSizeProduct(),itemProduct.getColorProduct(),itemProduct.getQuantityProduct());
                    oderList.add(tempProduct);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        rcViewItemListOrder.setAdapter(orderAdapter);


        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_order);
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
                        startActivity(new Intent(getApplicationContext(),Cart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ic_order:
                        return true;
                }
                return false;
            }
        });
    }
}