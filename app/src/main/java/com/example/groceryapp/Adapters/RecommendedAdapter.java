package com.example.groceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryapp.Models.HomeCategory;
import com.example.groceryapp.Models.Recommended;
import com.example.groceryapp.R;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.myViewHolder> {
    private Context context;
    List<Recommended> recommendedList;

    public RecommendedAdapter(Context context, List<Recommended> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    public void updateData(List<Recommended> recommendedList ){
        this.recommendedList = recommendedList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomended_item, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.name.setText(recommendedList.get(position).getName());
        holder.description.setText(recommendedList.get(position).getDescription());
        holder.rating.setText(recommendedList.get(position).getRating());
        Glide.with(holder.rec_img).load(recommendedList.get(position).getImg_url()).into(holder.rec_img);

    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView rec_img;
        TextView name, description, rating;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_img = itemView.findViewById(R.id.rec_img);
            name = itemView.findViewById(R.id.rec_name);
            description = itemView.findViewById(R.id.rec_des);
            rating = itemView.findViewById(R.id.rec_rating);

        }
    }
}
