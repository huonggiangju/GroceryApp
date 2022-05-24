package com.example.groceryapp.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groceryapp.Activities.PlaceOrderActivity;
import com.example.groceryapp.Adapters.MyCartAdapter;
import com.example.groceryapp.Models.MyCart;
import com.example.groceryapp.Models.PopularModel;
import com.example.groceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {

    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    List<MyCart> myCartList;

    FirebaseFirestore db;
    TextView totalPriceAmount;
    ScrollView scrollView;
    Button buyNow;

    public MyCartFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = root.findViewById(R.id.cart_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setVisibility(View.GONE);

        //scroll view
        scrollView = root.findViewById(R.id.scroll_view);
        scrollView.setVisibility(View.GONE);

        //getting total price amount
        totalPriceAmount = root.findViewById(R.id.total);
//        LocalBroadcastManager.getInstance(getActivity())
//                .registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));


        myCartList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getActivity(), myCartList);
        recyclerView.setAdapter(myCartAdapter);

        //connect DB
        db = FirebaseFirestore.getInstance();
        db.collection("CurrentUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String documentId = document.getId();
                                MyCart myCart = document.toObject(MyCart.class);

                                myCart.setDocumentId(documentId);
                                myCartList.add(myCart);
                                myCartAdapter.notifyDataSetChanged();

                                scrollView.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);

                            }

                            calculateToTalAmount(myCartList);
                        } else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //buy now Btn
        buyNow = root.findViewById(R.id.buyNowBtn);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PlaceOrderActivity.class);
                i.putExtra("itemList", (Serializable) myCartList);
                startActivity(i);
            }
        });

        return root;
    }

    private void calculateToTalAmount(List<MyCart> myCartList) {
        int totalAmount = 0;
        for(MyCart cart: myCartList){
            totalAmount += cart.getTotalPrice();
            totalPriceAmount.setText("Total amount: $"+totalAmount);
        }

    }

//    public  BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            int totalBill = intent.getIntExtra("totalAmount", 0);
//            totalPriceAmount.setText("Total Bill: $" + totalBill);
//        }
//    };
}