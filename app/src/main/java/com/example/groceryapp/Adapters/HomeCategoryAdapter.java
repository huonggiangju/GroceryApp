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
import com.example.groceryapp.Activities.ViewAllActivity;
import com.example.groceryapp.Models.HomeCategory;
import com.example.groceryapp.Models.PopularModel;
import com.example.groceryapp.R;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.myViewHolder> {
    private Context context;
    List<HomeCategory> homeCategoryList;

    public HomeCategoryAdapter(Context context, List<HomeCategory> homeCategoryList) {
        this.context = context;
        this.homeCategoryList = homeCategoryList;
    }

    public void updateData(List<HomeCategory> homeCategoryList ){
        this.homeCategoryList = homeCategoryList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_item, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.home_cat_name.setText(homeCategoryList.get(position).getName());
        Glide.with(holder.home_cat_img).load(homeCategoryList.get(position).getImg_url()).into(holder.home_cat_img);

        //click view listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewAllActivity.class);
                i.putExtra("type", homeCategoryList.get(holder.getAdapterPosition()).getType());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeCategoryList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView home_cat_img;
        TextView home_cat_name;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            home_cat_img = itemView.findViewById(R.id.home_cat_img);
            home_cat_name = itemView.findViewById(R.id.home_cat_name);

        }
    }
}
