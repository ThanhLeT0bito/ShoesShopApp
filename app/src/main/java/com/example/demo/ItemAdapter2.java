package com.example.demo;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter2 extends RecyclerView.Adapter<ItemAdapter2.ViewHolder> {
    Context context;
    int layout;
    ArrayList<Product> arrayList;

    public ItemAdapter2(Context context, int layout, ArrayList<Product> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        if (arrayList == null)
            return 0;
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName2, tvPrice2, tvBrief2;
        public ImageView ivImg2, imCart2;
        public LinearLayout itemLN;

        public ViewHolder(View v){
            super(v);
            tvName2 = v.findViewById(R.id.tvName2);
            tvBrief2 = v.findViewById(R.id.tvBrief2);
            tvPrice2 = v.findViewById(R.id.tvPrice2);
            ivImg2 = v.findViewById(R.id.ivImg2);
            imCart2 = v.findViewById(R.id.imCart2);
            itemLN = v.findViewById(R.id.item_view_2);
        }
    }

    @Override
    public ItemAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product items = arrayList.get(position);
        if (items == null)
            return;
        holder.tvName2.setText(items.getNameProduct());
        holder.tvBrief2.setText(items.getDescriptionProduct());
        holder.tvPrice2.setText(items.getPriceProduct());
        Picasso.with(this.context).load(items.getImgProduct()).into(holder.ivImg2);

        holder.imCart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference("Cart");
                String id;
                cartProduct cartProductTemp = new cartProduct(items.getIdProduct(), items.getNameProduct(),
                        items.getPriceProduct(),items.getImgProduct(),40,items.getColorProduct(), items.quantityProduct);
                mDatabase.child(items.getIdProduct()).setValue(items);
                Toast.makeText(context, "Đã thêm sản phẩm vào Giỏ hàng", Toast.LENGTH_LONG).show();
            }
        });

        holder.itemLN.setOnClickListener(new View.OnClickListener() {
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