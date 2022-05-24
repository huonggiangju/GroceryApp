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
import com.example.groceryapp.Activities.CatDetailActivity;
import com.example.groceryapp.Models.CategoryItem;
import com.example.groceryapp.Models.PopularModel;
import com.example.groceryapp.R;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.myViewHolder> {

    private Context context;
    private List<CategoryItem> categoryItemList;

    public CategoryItemAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }
    public void updateData(List<CategoryItem> categoryItemList){
        this.categoryItemList = categoryItemList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.name.setText(categoryItemList.get(position).getName());
        holder.discount.setText(categoryItemList.get(position).getDiscount());
        holder.description.setText(categoryItemList.get(position).getDescription());

        Glide.with(holder.imageView).load(categoryItemList.get(position).getImg_url()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CatDetailActivity.class);
                i.putExtra("type", categoryItemList.get(holder.getAdapterPosition()).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name, description, discount;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_item_img);
            name = itemView.findViewById(R.id.cat_item_name);
            description = itemView.findViewById(R.id.cat_item_des);
            discount = itemView.findViewById(R.id.cat_item_dis);

        }
    }
}
