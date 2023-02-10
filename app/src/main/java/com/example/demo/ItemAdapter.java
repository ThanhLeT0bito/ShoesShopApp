package com.example.demo;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    int layout;
    ArrayList<Product> arrayList;


    public ItemAdapter(Context context, int layout, ArrayList<Product> arrayList) {
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
        public TextView tvName, tvPrice, tvSoldout;
        public ImageView ivImg, ivAddCart;
        public LinearLayout itemView;

        public ViewHolder(View v){
            super(v);
            tvName = v.findViewById(R.id.tvName);
            tvPrice = v.findViewById(R.id.tvPrice);
            tvSoldout = v.findViewById(R.id.tvSoldout);
            ivImg = v.findViewById(R.id.ivImg);
            itemView = v.findViewById(R.id.item_view);
            ivAddCart = v.findViewById(R.id.addCart);
        }
    }
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        final ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

@SuppressLint("ResourceType")
@Override
public void onBindViewHolder(ViewHolder holder, int position) {
        Product items = arrayList.get(position);
        if (items == null)
            return;
        holder.tvName.setText(items.getNameProduct());
        holder.tvPrice.setText(items.getPriceProduct());
        String tvSoldOutTemp = "Đã bán: " +String.valueOf(items.getQuantitySoldOutProduct());
        holder.tvSoldout.setText(tvSoldOutTemp);

        Picasso.with(this.context).load(items.getImgProduct()).into(holder.ivImg);


        holder.ivAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference("Cart");
                String id;
                cartProduct cartProductTemp = new cartProduct(items.getIdProduct(), items.getNameProduct(),
                        items.getPriceProduct(),items.getImgProduct(),40,items.getColorProduct(), items.quantityProduct);
                mDatabase.child(items.getIdProduct()).setValue(cartProductTemp);
                Toast.makeText(context, "Đã thêm sản phẩm vào Giỏ hàng", Toast.LENGTH_LONG).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Detail.class);
                i.putExtra("idProduct",items.getIdProduct());
                i.putExtra("name",items.getNameProduct());
                i.putExtra("price",items.getPriceProduct());
                i.putExtra("imgUri",items.getImgProduct());
                i.putExtra("desc",items.getDescriptionProduct());
//                i.putExtra("quantitySoldOutProduct",String.valueOf( items.getQuantitySoldOutProduct()));
                context.startActivity(i);
            }
        });
    }
}
