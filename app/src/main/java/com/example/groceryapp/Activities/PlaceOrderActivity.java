package com.example.groceryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.groceryapp.Models.MyCart;
import com.example.groceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        firestore = FirebaseFirestore.getInstance();

        List<MyCart> myCartList = (ArrayList<MyCart>) getIntent().getSerializableExtra("itemList");

        if(myCartList != null && myCartList.size() >0){
            for(MyCart cart: myCartList){
                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("productName", cart.getProductName());
                cartMap.put("productPrice", cart.getProductPrice());
                cartMap.put("currentDate", cart.getCurrentDate());
                cartMap.put("currentTime", cart.getCurrentTime());
                cartMap.put("totalQuantity", cart.getTotalQuantity());
                cartMap.put("totalPrice", cart.getTotalPrice());


                //store cart into firestore
                firestore.collection("MyOrder")
                        .add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlaceOrderActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    }
}