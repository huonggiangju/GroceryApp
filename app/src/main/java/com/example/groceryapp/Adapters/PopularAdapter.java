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
import com.example.groceryapp.Models.PopularModel;
import com.example.groceryapp.R;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.myViewHolder> {
    private Context context;
    List<PopularModel> popularModelList;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    public void updateData(List<PopularModel> popularModelList ){
        this.popularModelList = popularModelList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.pop_name.setText(popularModelList.get(position).getName());
        holder.pop_des.setText(popularModelList.get(position).getDescription());
        holder.pop_rating.setText(popularModelList.get(position).getRating());
        holder.pop_discount.setText(popularModelList.get(position).getDiscount());

        Glide.with(holder.popImg).load(popularModelList.get(position).getImg_url()).into(holder.popImg);

        //click view listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewAllActivity.class);
                i.putExtra("type", popularModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView popImg;
        TextView pop_name, pop_des, pop_rating, pop_discount;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            pop_name = itemView.findViewById(R.id.pop_name);
            pop_rating = itemView.findViewById(R.id.pop_rating);
            pop_des = itemView.findViewById(R.id.pop_des);
            pop_discount = itemView.findViewById(R.id.pop_discount);
        }
    }
}
