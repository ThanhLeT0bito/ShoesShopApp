package com.example.demo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcViewItemList, rcViewItemList2, rcViewItemList3;
    private RecyclerView.LayoutManager layoutItemList, layoutItemList2, layoutItemList3;
    private ArrayList<Product> itemsArrayList, itemsArrayList2, itemsArrayList3;
    private ItemAdapter itemAdapter;
    private ItemAdapter2 itemAdapter2;
    private ItemAdapter3 itemAdapter3;
    SearchView searchView;
    BottomNavigationView bottomNavigationView;
    int selectedBrand =0;
    //

    private FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///
        TextView edBrand = findViewById(R.id.brand);
//        FirebaseAuth.getInstance().signOut();

        edBrand.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });
        searchView = findViewById(R.id.searchbar);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });

        RC_BrandList_Handle();
        RC_NRList_Handle();
        RC_TTList_Handle();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.ic_user:

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
                        return true;

                    case R.id.ic_cart:
                        startActivity(new Intent(getApplicationContext(),Cart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ic_order:
                        startActivity(new Intent(getApplicationContext(), Order.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    private  void initFirebase(){
        FirebaseApp.initializeApp(this);

//        FirebaseAppCheck
    }
    @SuppressLint("ResourceAsColor")
    public void RC_BrandList_Handle(){
        rcViewItemList = findViewById(R.id.brand_item_list);
        layoutItemList = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcViewItemList.setLayoutManager(layoutItemList);
        itemsArrayList = new ArrayList<>();

        itemAdapter = new ItemAdapter(MainActivity.this,
                R.layout.brand_item_list,
                itemsArrayList);
        TextView bl_all, bl_nike, bl_puma,bl_jordan,bl_addidas,bl_reebok, bl_converse;
        bl_all = findViewById(R.id.bl_all);
        bl_nike = findViewById(R.id.bl_nike);
        bl_puma = findViewById(R.id.bl_puma);
        bl_converse = findViewById(R.id.bl_converse);
        bl_jordan = findViewById(R.id.bl_jordan);
        bl_addidas = findViewById(R.id.bl_addidas);
        bl_reebok = findViewById(R.id.bl_reebok);
        String[] brandSelect ={"Products","Brand/Nike","Brand/Puma","Brand/Jordan",
                "Brand/Addidas","Brand/Reebok","Brand/Converse"};
        TextView[] setTextBrand={bl_all,bl_nike,bl_puma,bl_jordan,bl_addidas,bl_reebok,bl_converse};



        bl_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBrand = 0;
                RC_BrandList_Handle();
            }
        });
        bl_nike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBrand = 1;
                RC_BrandList_Handle();

            }
        });
        bl_puma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBrand = 2;
                RC_BrandList_Handle();

            }
        });
        bl_jordan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBrand = 3;
                RC_BrandList_Handle();
            }
        });
        bl_addidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBrand = 4;
                RC_BrandList_Handle();
            }
        });
        bl_reebok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBrand = 5;
                RC_BrandList_Handle();
            }
        });
        bl_converse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBrand = 6;
                RC_BrandList_Handle();
            }
        });

        for (int i=0; i<7; i++){
            if (i == selectedBrand){
                setTextBrand[i].setTypeface(setTextBrand[i].getTypeface(), Typeface.BOLD);
            }
            else {
                setTextBrand[i].setTypeface(setTextBrand[i].getTypeface(), Typeface.ITALIC);
            }

        }

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference(brandSelect[selectedBrand]);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemsArrayList.removeAll(itemsArrayList);
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Product brandListFB = postSnapshot.getValue(Product.class);
                    itemsArrayList.add(brandListFB);
                }
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        rcViewItemList.setAdapter(itemAdapter);
    }

    public void RC_NRList_Handle(){
        rcViewItemList2 = findViewById(R.id.newrelease_item_list);
        layoutItemList2 = new LinearLayoutManager(this);
        rcViewItemList2.setLayoutManager(layoutItemList2);
        itemsArrayList2 = new ArrayList<>();
        itemAdapter2 = new ItemAdapter2(MainActivity.this,
                R.layout.nr_item_list,
                itemsArrayList2);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Products");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemsArrayList2.removeAll(itemsArrayList2);
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Product brandListFB = postSnapshot.getValue(Product.class);
                    itemsArrayList2.add(brandListFB);
                }
                Collections.reverse(itemsArrayList2);
                itemAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        rcViewItemList2.setAdapter(itemAdapter2);
    }

    public void RC_TTList_Handle(){
        rcViewItemList3 = findViewById(R.id.toptrend_item_list);
        layoutItemList3 = new GridLayoutManager(this, 2);
        rcViewItemList3.setLayoutManager(layoutItemList3);
        itemsArrayList3 = new ArrayList<>();
        itemAdapter3 = new ItemAdapter3(MainActivity.this,
                R.layout.toptrend_item_list,
                itemsArrayList3);

        DatabaseReference mDatabase3;
        mDatabase3 = FirebaseDatabase.getInstance().getReference();
        Query qProduct = mDatabase3.child("Products");
        qProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemsArrayList3.removeAll(itemsArrayList3);
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Product brandListFB = postSnapshot.getValue(Product.class);
                    itemsArrayList3.add(brandListFB);
                }
                itemAdapter3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Ko lay dc data realtime", Toast.LENGTH_SHORT).show();
            }
        });
        rcViewItemList3.setAdapter(itemAdapter3);
    }

}
