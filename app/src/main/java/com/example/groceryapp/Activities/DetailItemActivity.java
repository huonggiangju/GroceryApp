package com.example.groceryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.groceryapp.Models.MyCart;
import com.example.groceryapp.Models.ViewAllItem;
import com.example.groceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailItemActivity extends AppCompatActivity {

    ImageView detailImg, addImg, removerImg;
    TextView price, rating, description, quanity;
    Button addToCartBtn;
    Toolbar toolbar1;

    ViewAllItem viewAllItem = null;
    int totalQuantity = 1;
    int totalPrice = 0;

    FirebaseFirestore firestore;
    FirebaseDatabase database;
    MyCart myCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        detailImg = findViewById(R.id.detailImg);
        addImg = findViewById(R.id.add_cir);
        removerImg = findViewById(R.id.remove_cir);
        price = findViewById(R.id.detail_price);
        rating = findViewById(R.id.detail_rating);
        description = findViewById(R.id.detail_des);
        addToCartBtn = findViewById(R.id.addToCart);
        quanity = findViewById(R.id.quantity);

        firestore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();



        //toolbar
        toolbar1 = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar1);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getting detail intent
        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllItem){
            viewAllItem = (ViewAllItem) object;
        }

        if(viewAllItem != null){
            Glide.with(getApplicationContext()).load(viewAllItem.getImg_url()).into(detailImg);
            rating.setText(viewAllItem.getRating());

            price.setText(String.valueOf("Price: $" + viewAllItem.getPrice() + "/kg"));

            totalPrice = viewAllItem.getPrice() * totalQuantity;
            if(viewAllItem.getType().equals("egg")){
                price.setText("Price: $" + String.valueOf(viewAllItem.getPrice()) + "/dozen");
            }
            if(viewAllItem.getType().equals("milk")){
                price.setText("Price: $" + String.valueOf(viewAllItem.getPrice()) + "/litre");
            }
            description.setText(viewAllItem.getDescription());
        }

        //add and remove btn
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity < 10){
                    totalQuantity++;
                    quanity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllItem.getPrice() * totalQuantity;
                }
            }
        });

        removerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity > 1){
                    totalQuantity--;
                    quanity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllItem.getPrice() * totalQuantity;
                }
            }
        });

        //add to Cart btn
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCart();
            }
        });
    }

    //comeback to previous page on action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //addTo carr
    private void addedToCart(){
        String saveCurrentDate, saveCurrentTime;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName", viewAllItem.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quanity.getText().toString());
        cartMap.put("totalPrice", totalPrice);


      //store cart into firestore
        firestore.collection("CurrentUser")
                .add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailItemActivity.this, "Add to the Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}