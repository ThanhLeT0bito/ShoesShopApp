package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.ViewHolder>{
    Context context;
    int layout;
    ArrayList<orderProduct> arrayList;


    public orderAdapter(Context context, int layout, ArrayList<orderProduct> arrayList) {
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
        public TextView tvTenSP, tvSize,tvColor, tvQuantity,item_order_total,item_order_DateTime;
        public LinearLayout orderitemlist;
        public Button btnOrderDetails;

        public ViewHolder(View v){
            super(v);
            tvTenSP = v.findViewById(R.id.item_order_name);
            tvSize = v.findViewById(R.id.item_order_size);
            tvColor = v.findViewById(R.id.item_order_color);
            tvQuantity = v.findViewById(R.id.item_order_quantity);
            btnOrderDetails = v.findViewById(R.id.btn_order_details);
            item_order_total = v.findViewById(R.id.item_order_total);
            item_order_DateTime = v.findViewById(R.id.item_order_DateTime);
//            orderitemlist = v.findViewById(R.id.order_item_list);
        }
    }
    @Override
    public orderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        final orderAdapter.ViewHolder vHolder = new orderAdapter.ViewHolder(v);
        return vHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(orderAdapter.ViewHolder holder, int position) {
        orderProduct items = arrayList.get(position);
        if (items == null)
            return;
        holder.tvTenSP.setText(items.getNameProduct());
        holder.tvSize.setText(String.valueOf(items.getSizeProduct()));
        holder.tvColor.setText(items.getColorProduct());
        holder.tvQuantity.setText(String.valueOf(items.getQuantityProduct()));
        double total =0.0;
        String temp = items.getPriceProduct();
        temp = temp.replaceAll(",","");
        Double totalTemp = Double.parseDouble(temp) * items.getQuantityProduct();
        total += totalTemp;
        totalTemp=0.0;
        holder.item_order_total.setText(String.valueOf(total));
        ///
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        holder.item_order_DateTime.setText(currentDate);

    }

}


