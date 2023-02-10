package com.example.demo;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter3 extends RecyclerView.Adapter<ItemAdapter3.ViewHolder> {
    Context context;
    int layout;
    ArrayList<Product> arrayList;

    public ItemAdapter3(Context context, int layout, ArrayList<Product> arrayList) {
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
        public TextView tvName3, tvPrice3, tvSoldout3;
        public ImageView ivImg3, imAddCart3;
        public LinearLayout item_view;

        public ViewHolder(View v){
            super(v);
            tvName3 = v.findViewById(R.id.tvName3);
            tvSoldout3 = v.findViewById(R.id.tvSoldout3);
            tvPrice3 = v.findViewById(R.id.tvPrice3);
            ivImg3 = v.findViewById(R.id.ivImg3);
            imAddCart3 = v.findViewById(R.id.imAddCart3);
            item_view = v.findViewById(R.id.item_view);
        }
    }

    @Override
    public ItemAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product items = arrayList.get(position);
        holder.tvName3.setText(items.getNameProduct());
        holder.tvSoldout3.setText("Đã bán: " + String.valueOf(items.getQuantitySoldOutProduct()));
        holder.tvPrice3.setText(items.getPriceProduct());
        Picasso.with(this.context).load(items.getImgProduct()).into(holder.ivImg3);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Detail.class);
                i.putExtra("name",items.getNameProduct());
                i.putExtra("price",items.getPriceProduct());
                i.putExtra("imgUri",items.getImgProduct());
                i.putExtra("desc",items.getDescriptionProduct());
                context.startActivity(i);
            }
        });
    }
}