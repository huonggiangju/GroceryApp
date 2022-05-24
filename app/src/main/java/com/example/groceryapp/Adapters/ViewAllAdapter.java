package com.example.groceryapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryapp.Activities.DetailItemActivity;
import com.example.groceryapp.Models.ViewAllItem;
import com.example.groceryapp.R;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.myViewHolder> {

    Context context;
    List<ViewAllItem> viewAllItemList;

    public ViewAllAdapter(Context context, List<ViewAllItem> viewAllItemList) {
        this.context = context;
        this.viewAllItemList = viewAllItemList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item_row, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.name.setText(viewAllItemList.get(position).getName());
        holder.description.setText(viewAllItemList.get(position).getDescription());
        holder.rating.setText(viewAllItemList.get(position).getRating());

        holder.price.setText(String.valueOf(viewAllItemList.get(position).getPrice()) + "/kg");
        if(viewAllItemList.get(position).getType().equals("egg")){
            holder.price.setText(String.valueOf(viewAllItemList.get(position).getPrice()) + "/dozen");
        }
        if(viewAllItemList.get(position).getType().equals("milk")){
            holder.price.setText(String.valueOf(viewAllItemList.get(position).getPrice()) + "/litre");
        }

        Glide.with(holder.imgItem).load(viewAllItemList.get(position).getImg_url()).into(holder.imgItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailItemActivity.class);
                i.putExtra("detail", viewAllItemList.get(holder.getAdapterPosition()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllItemList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgItem;
        TextView name, description, price, rating;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.view_all_img);
            name = itemView.findViewById(R.id.view_all_item_name);
            description = itemView.findViewById(R.id.view_all_item_des);
            price = itemView.findViewById(R.id.view_all_item_price);
            rating = itemView.findViewById(R.id.view_all_rating);
        }
    }
}
