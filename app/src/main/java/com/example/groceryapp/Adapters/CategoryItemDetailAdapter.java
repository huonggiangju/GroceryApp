package com.example.groceryapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryapp.Activities.CatDetailActivity;
import com.example.groceryapp.Models.CategoryItemDetail;
import com.example.groceryapp.R;

import java.util.List;

public class CategoryItemDetailAdapter extends RecyclerView.Adapter<CategoryItemDetailAdapter.myViewHolder> {

    Context context;
    List<CategoryItemDetail> list;

    public CategoryItemDetailAdapter(Context context, List<CategoryItemDetail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_detail_row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.price.setText(String.valueOf(list.get(position).getPrice()));

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imgView);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView, addCir,removeCir;
        TextView name, price, quantity;
        Button buyNowBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.img);
            addCir = itemView.findViewById(R.id.add_cir);
            removeCir = itemView.findViewById(R.id.remove_cir);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            quantity = itemView.findViewById(R.id.quantity);
            buyNowBtn = itemView.findViewById(R.id.buyNowBtn);

        }
    }
}
