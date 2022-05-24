package com.example.groceryapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.Models.MyCart;
import com.example.groceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.myViewHolder> {
    Context context;
    List<MyCart> myCartList;
    int totalPriceAmount = 0;
    FirebaseFirestore firestore;

    public MyCartAdapter(Context context, List<MyCart> myCartList) {
        this.context = context;
        this.myCartList = myCartList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.name.setText(myCartList.get(position).getProductName());
        holder.price.setText(myCartList.get(position).getProductPrice());
        holder.date.setText(myCartList.get(position).getCurrentDate());
        holder.time.setText(myCartList.get(position).getCurrentTime());
        holder.quantity.setText(myCartList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(myCartList.get(position).getTotalPrice()));

//        //passing total price amount into my cart fragment
//        totalPriceAmount = totalPriceAmount +myCartList.get(position).getTotalPrice();
//        Intent i = new Intent("MyTotalAmount");
//        i.putExtra("totalAmount", totalPriceAmount);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(i);

        //detele btb
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("CurrentUser")
                        .document(myCartList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    myCartList.remove(myCartList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return myCartList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView name, price, date, time, quantity, totalPrice;
        ImageView delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.pro_name);
            price = itemView.findViewById(R.id.pro_price);
            date = itemView.findViewById(R.id.currentDate);
            time = itemView.findViewById(R.id.currentTime);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
