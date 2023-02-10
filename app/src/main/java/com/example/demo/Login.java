package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText edUser, edPass;
    Button btnLogin;
    TextView chuacotaikhoan_DK;
    private String email, pass;
    boolean check = false;

    //firebase
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set text when sign up succesful
        Intent intent = getIntent();
        String name ="", pass="";
        name = intent.getStringExtra("userName");
        pass = intent.getStringExtra("pass");

        BottomNavigationView bottomNavigationView;

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

        edUser = findViewById(R.id.edNameLogin);
        edPass = findViewById(R.id.edPassLogin);

        //set Text name pass
        edUser.setText(name);
        edPass.setText(pass);

        btnLogin = findViewById(R.id.btnLogin);
        chuacotaikhoan_DK = findViewById(R.id.chuacotaikhoan_DK);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                checkLogin();
            }
        });
        chuacotaikhoan_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }
    public void  checkLogin(){
        email = edUser.getText().toString();
        pass = edPass.getText().toString();

        if (email.isEmpty()){
            edUser.setError("User can not be empty...");
        }
        if (pass.isEmpty()){
            edPass.setError("Pass can not be empty...");
        }else{
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Login.this, "Sign Up Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finish();
//                                Log.d(TAG, "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                                Toast.makeText(Login.this, "Sign Up Failed" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }

}