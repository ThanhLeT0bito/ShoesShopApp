package com.example.demo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Search extends AppCompatActivity {

    private RecyclerView rcViewItemList;
    private RecyclerView.LayoutManager layoutItemList;
    private ArrayList<Product> itemsArrayList;
    private ItemAdapter3 itemAdapter;
    EditText edtSearchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtSearchBar = findViewById(R.id.edtSearchBar);

        ImageView imbtnsearch = findViewById(R.id.imbtnsearch);
        imbtnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RC_TTList_Handle();
            }
        });
    }
    public void RC_TTList_Handle(){
        rcViewItemList = findViewById(R.id.search_item_list);
        layoutItemList = new GridLayoutManager(this, 2);
        rcViewItemList.setLayoutManager(layoutItemList);
        itemsArrayList = new ArrayList<>();
        itemAdapter = new ItemAdapter3(Search.this,
                R.layout.toptrend_item_list,
                itemsArrayList);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Products");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemsArrayList.removeAll(itemsArrayList);
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Product items = postSnapshot.getValue(Product.class);
                    String searchText = edtSearchBar.getText().toString().toLowerCase();
                    if (items.getNameProduct().toString().toLowerCase().trim().contains(searchText)){
                        itemsArrayList.add(items);
                    }
                }
                itemAdapter.notifyDataSetChanged();
            }

            //mQuery = mDatabase.orderByChild("Title").equalTo("your value")
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        rcViewItemList.setAdapter(itemAdapter);
    }
}