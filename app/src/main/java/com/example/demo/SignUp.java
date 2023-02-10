package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class SignUp extends AppCompatActivity {
    EditText edUser, edPass, edNhapLaiPass;
    Button btnSignUp;
    private String email, pass, pass1;
    TextView cotaikhoan_DN;
    //firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ///
        edUser = findViewById(R.id.edNameSU);
        edPass = findViewById(R.id.edPass);
        edNhapLaiPass = findViewById(R.id.edNhapLaiPass);
        btnSignUp = findViewById(R.id.btnSignUp);
        //firebase
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                addUser(view);
                checkSignUp();
            }
        });

        cotaikhoan_DN = findViewById(R.id.cotaikhoan_DN);

        cotaikhoan_DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });

    }

    public void  checkSignUp(){
        email = edUser.getText().toString();
        pass = edPass.getText().toString();
        pass1 = edNhapLaiPass.getText().toString();

        if (email.isEmpty()){
            edUser.setError("User can not be empty...");
        }
        else if  (pass.isEmpty()){
            edPass.setError("Pass can not be empty...");
        }
        else if (pass1.isEmpty()){
            edNhapLaiPass.setError("Confirm Pass can not be empty...");
        }
        else if (!pass.equals(pass1)){
//            Toast.makeText(Signin.this, pass + "  " + pass1, Toast.LENGTH_LONG).show();
            Toast.makeText(SignUp.this, "Confirm Pass Error ", Toast.LENGTH_LONG).show();
            return;
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(SignUp.this, "Sign Up Successfully", Toast.LENGTH_LONG).show();
                                final String randomKey = UUID.randomUUID().toString();
                                String tempKey = mAuth.getUid();
                                userProfile userTemp = new userProfile(tempKey, email, pass,"","");
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("users").child(tempKey).setValue(userTemp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(SignUp.this, "Put Info to Firebase", Toast.LENGTH_SHORT).show();
//                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignUp.this, "Can't put Info to Firebase", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Intent i = new Intent(SignUp.this, Login.class);
                                i.putExtra("userName",email);
                                i.putExtra("pass",pass);
                                startActivity(i);


                            } else {
                                Toast.makeText(SignUp.this, "Sign Up Failed" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }
}
