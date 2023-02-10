package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder>{
    Context context;
    int layout;
    ArrayList<cartProduct> arrayList;


    public cartAdapter(Context context, int layout, ArrayList<cartProduct> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        if (arrayList.isEmpty())
            return 0;
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTenSP, tvGia, tvSoldout,tvSize,tvColor, item_cart_reduce, item_cart_add, item_cart_quanlity;
        public ImageView ivImgCartProduct;
        public LinearLayout itemView;

        public ViewHolder(View v){
            super(v);
            tvTenSP = v.findViewById(R.id.tvTenSP);
            tvGia = v.findViewById(R.id.tvGia);
            tvSoldout = v.findViewById(R.id.tvSoldout);
            ivImgCartProduct = v.findViewById(R.id.ivImgCartProduct);
            tvSize = v.findViewById(R.id.tvSize);
            tvColor = v.findViewById(R.id.tvColor);
            itemView = v.findViewById(R.id.item_view);
            item_cart_reduce = v.findViewById(R.id.item_cart_reduce);
            item_cart_add = v.findViewById(R.id.item_cart_add);
            item_cart_quanlity = v.findViewById(R.id.item_cart_quanlity);
        }
    }
    @Override
    public cartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        final cartAdapter.ViewHolder vHolder = new cartAdapter.ViewHolder(v);
        return vHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(cartAdapter.ViewHolder holder, int position) {
        cartProduct items = arrayList.get(position);
        if (items == null)
            return;
        holder.tvTenSP.setText(items.getNameProduct());
        holder.tvGia.setText(items.getPriceProduct());
        holder.tvSize.setText("Kích Cỡ: "+ String.valueOf(items.getSizeProduct()));
        holder.tvColor.setText("Màu Sắc: " + items.getColorProduct());
        //
        DatabaseReference mDatabase;
        String pathTemp = "Cart/" + items.getIdProduct();
        mDatabase = FirebaseDatabase.getInstance().getReference(pathTemp);

        holder.item_cart_quanlity.setText(String.valueOf(items.quantityProduct));

//        final int[] quanlity = {1};
        holder.item_cart_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.getQuantityProduct() > 1){
                    items.quantityProduct--;
                    holder.item_cart_quanlity.setText(String.valueOf(items.quantityProduct));
                    mDatabase.child("quantityProduct").setValue(items.getQuantityProduct());
                } else {
                    items.quantityProduct=0;
                    mDatabase.child("quantityProduct").setValue(0);
                    mDatabase.removeValue();
                }
            }
        });

        holder.item_cart_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.quantityProduct++;
                holder.item_cart_quanlity.setText(String.valueOf(items.quantityProduct));
                mDatabase.child("quantityProduct").setValue(items.getQuantityProduct());
            }
        });

        Picasso.with(this.context).load(items.getImgProduct()).into(holder.ivImgCartProduct);
    }

}

