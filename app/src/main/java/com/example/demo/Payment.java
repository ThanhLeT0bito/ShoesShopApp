package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Payment extends AppCompatActivity {

    ImageView backbtn;
    int quanlity =1;
    Double total =0.0, price =0.0;
    TextView tvTotalTempPay,tvTotalPay;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ImageView imgPayment;
        TextView tvTenSPPay,tvKichCoPay,tvMauSacPay,tvGiaPay,reduce_pay,quanlity_pay,add_pay;
        String size;

        imgPayment = findViewById(R.id.imgPayment);
        tvTenSPPay = findViewById(R.id.tvTenSPPay);
        tvKichCoPay = findViewById(R.id.tvKichCoPay);
        tvMauSacPay = findViewById(R.id.tvMauSacPay);
        tvGiaPay = findViewById(R.id.tvGiaPay);
        //
        reduce_pay = findViewById(R.id.reduce_pay);
        quanlity_pay = findViewById(R.id.quanlity_pay);
        add_pay = findViewById(R.id.add_pay);
        tvTotalTempPay = findViewById(R.id.tvTotalTempPay);
        tvTotalPay = findViewById(R.id.tvTotalPay);


        Intent intent = getIntent();
        String idProduct = intent.getStringExtra("idProduct");
        size = intent.getStringExtra("size");
        String quanTemp = intent.getStringExtra("quanlity");
        quanlity_pay.setText(quanTemp);
        quanlity = Integer.valueOf(quanTemp);
        tvKichCoPay.setText("Kích Cỡ: "+size);
        ///
        reduce_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanlity > 1) {
                    quanlity--;
                }
                quanlity_pay.setText(String.valueOf(quanlity));
                changeTotal();
            }
        });

        /// add quanlity product
        add_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanlity++;
                quanlity_pay.setText(String.valueOf(quanlity));
                changeTotal();
            }
        });


        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Products/"+idProduct);
        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                     if(task .isSuccessful()){
                         Product temp = task.getResult().getValue(Product.class);
                         Picasso.with(Payment.this).
                                 load(temp.getImgProduct().toString()).into(imgPayment);
                         tvTenSPPay.setText(temp.getNameProduct().toString());
                         tvMauSacPay.setText("Màu Sắc: " + temp.getColorProduct().toString());
                         tvGiaPay.setText(temp.getPriceProduct().toString());
                         ///
                         String giaTemp = temp.getPriceProduct();
                         String gia  = giaTemp.replaceAll(",","");
                         price = Double.valueOf(gia);
                         changeTotal();
                     }
            }
        });

        backbtn = findViewById(R.id.imgbackbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Payment.this, Detail.class);
                startActivity(i);
            }
        });


    }
    void changeTotal(){
        total = 0.0;
        total = price * quanlity;
        tvTotalTempPay.setText(String.format("%.2f",total) + " VND");
        tvTotalPay.setText(String.format("%.2f",total) + " VND");
    }
}