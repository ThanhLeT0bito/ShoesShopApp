package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    Button btnSignOut, btnChange;
    EditText edNameUserProfile,edPassProfile,edAddressUser,edPhoneUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        BottomNavigationView bottomNavigationView;

        ///Sign out
        btnSignOut = findViewById(R.id.btnsignOut);
        edNameUserProfile = findViewById(R.id.edNameUserProfile);
        edAddressUser = findViewById(R.id.edAddressUser);
        edPassProfile = findViewById(R.id.edPassProfile);

        // change in4
        btnChange = findViewById(R.id.btnChange);
        edPhoneUser = findViewById(R.id.edPhoneUser);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference("users/"+user.getUid());
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userProfile userTemp = snapshot.getValue(userProfile.class);
                    String nameEmail = userTemp.getEmail().toString();
                    String namePass = userTemp.getPass().toString();
                    String nameAddress = userTemp.getAddress().toString();
                    String nameNumPhone = userTemp.getNumPhone().toString();
                    edNameUserProfile.setText(nameEmail);
                    edPassProfile.setText(namePass);
                    edAddressUser.setText(nameAddress);
                    edPhoneUser.setText(nameNumPhone);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null){
                    DatabaseReference mDatabase;
                    mDatabase = FirebaseDatabase.getInstance().getReference("users/"+user.getUid());
                    userProfile userTemp = new userProfile(user.getUid().toString(),user.getEmail().toString(),edPassProfile.getText().toString(),
                            edAddressUser.getText().toString(), edPhoneUser.getText().toString());
                    mDatabase.setValue(userTemp);
                    Toast.makeText(User.this, "Cập nhật thành công!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(User.this,MainActivity.class));
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_user);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.ic_user:
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
                        startActivity(new Intent(getApplicationContext(),Order.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}