package com.example.demo;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    TextView tvNameDetail, tvPriceDetail, tvDesc, tvSoldOut, reduceQuanlity, addQuanlity, detailQuanlity;
    //size textview
    TextView tvSize39,tvSize40,tvSize41,tvSize42,tvSize43;
    ImageView imgDetail, backbtn;
    String idProduct, name, price, imgUri, descript, soldout;
    Button buynow;
    int size =40;
    int quanlity =1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNameDetail = findViewById(R.id.tvNameDetail);
        tvPriceDetail = findViewById(R.id.tvPriceDetail);
        imgDetail = findViewById(R.id.imgDetail);
        backbtn = findViewById(R.id.imgbackbtn);
        tvDesc = findViewById(R.id.tvDesc);
        buynow = findViewById(R.id.buynow);
        reduceQuanlity = findViewById(R.id.reduceQuanlity);
        addQuanlity = findViewById(R.id.addQuanlity);
        detailQuanlity = findViewById(R.id.detailQuanlity);
        //
        tvSize39 = findViewById(R.id.tvSize39);
        tvSize40 = findViewById(R.id.tvSize40);
        tvSize41 = findViewById(R.id.tvSize41);
        tvSize42 = findViewById(R.id.tvSize42);
        tvSize43 = findViewById(R.id.tvSize43);

        tvSize39.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                size = 39;
                tvSize39.setTextColor(Color.parseColor("#F30000"));
                //
                tvSize40.setTextColor(Color.parseColor("#FF000000"));
                tvSize41.setTextColor(Color.parseColor("#FF000000"));
                tvSize42.setTextColor(Color.parseColor("#FF000000"));
                tvSize43.setTextColor(Color.parseColor("#FF000000"));

            }
        });
        tvSize40.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                size = 40;
                tvSize40.setTextColor(Color.parseColor("#F30000"));
                //
                tvSize39.setTextColor(Color.parseColor("#FF000000"));
                tvSize41.setTextColor(Color.parseColor("#FF000000"));
                tvSize42.setTextColor(Color.parseColor("#FF000000"));
                tvSize43.setTextColor(Color.parseColor("#FF000000"));

            }
        });
        tvSize41.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                size = 41;
                tvSize41.setTextColor(Color.parseColor("#F30000"));
                //
                tvSize39.setTextColor(Color.parseColor("#FF000000"));
                tvSize40.setTextColor(Color.parseColor("#FF000000"));
                tvSize42.setTextColor(Color.parseColor("#FF000000"));
                tvSize43.setTextColor(Color.parseColor("#FF000000"));

            }
        });
        tvSize42.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                size = 42;
                tvSize42.setTextColor(Color.parseColor("#F30000"));
                //
                tvSize39.setTextColor(Color.parseColor("#FF000000"));
                tvSize40.setTextColor(Color.parseColor("#FF000000"));
                tvSize41.setTextColor(Color.parseColor("#FF000000"));
                tvSize43.setTextColor(Color.parseColor("#FF000000"));

            }
        });
        tvSize43.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                size = 43;
                tvSize43.setTextColor(Color.parseColor("#F30000"));
                //
                tvSize39.setTextColor(Color.parseColor("#FF000000"));
                tvSize40.setTextColor(Color.parseColor("#FF000000"));
                tvSize41.setTextColor(Color.parseColor("#FF000000"));
                tvSize42.setTextColor(Color.parseColor("#FF000000"));

            }
        });


        Intent intent = getIntent();

        idProduct = intent.getStringExtra("idProduct");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        imgUri = intent.getStringExtra("imgUri");
        descript = intent.getStringExtra("desc");
//        soldout = intent.getStringExtra("quantitySoldOutProduct").toString();

        tvNameDetail.setText(name.toString());
        tvPriceDetail.setText(price.toString() + " VND");
        tvDesc.setText(descript.toString());
//        tvSoldOut.setText(soldout.toString());
        Picasso.with(this).
                load(imgUri).into(imgDetail);

        /// reduce quanlity product
        reduceQuanlity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanlity > 1) {
                    quanlity--;
                }
                detailQuanlity.setText(String.valueOf(quanlity));
            }
        });

        /// add quanlity product
        addQuanlity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanlity++;
                detailQuanlity.setText(String.valueOf(quanlity));
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Detail.this, MainActivity.class);
                startActivity(i);
            }
        });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Detail.this, Payment.class);
                i.putExtra("idProduct",idProduct);
//                i.putExtra("name",name);
//                i.putExtra("price",price);
//                i.putExtra("imgUri",imgUri);
                i.putExtra("quanlity",String.valueOf(quanlity));
                i.putExtra("size",String.valueOf(size));
                startActivity(i);
            }
        });
    }
}