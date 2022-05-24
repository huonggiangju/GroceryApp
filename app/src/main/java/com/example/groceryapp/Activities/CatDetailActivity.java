package com.example.groceryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.groceryapp.Adapters.CategoryItemDetailAdapter;
import com.example.groceryapp.Models.CategoryItemDetail;

import com.example.groceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CatDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryItemDetailAdapter adapter;
    List<CategoryItemDetail> categoryItemDetailList;
    FirebaseFirestore db;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        //getting intent from popular product
        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.cat_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryItemDetailList = new ArrayList<>();
        adapter = new CategoryItemDetailAdapter(this, categoryItemDetailList);
        recyclerView.setAdapter(adapter);

        //toolbar
//        toolbar = findViewById(R.id.detail_toolbar);
//        setSupportActionBar(toolbar);



        //connect DB
        db = FirebaseFirestore.getInstance();
        //======getting fruit
        if(type != null && type.equalsIgnoreCase("fruit")){
            db.collection("CategoryItemDetail")
                    .whereEqualTo("type", "fruit")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                CategoryItemDetail categoryItemDetail = documentSnapshot.toObject(CategoryItemDetail.class);
                                categoryItemDetailList.add(categoryItemDetail);
                                adapter.notifyDataSetChanged();
                            }
                        }

                    });
        }

//        ======getting drink
        if(type != null && type.equalsIgnoreCase("drink")){
            db.collection("CategoryItemDetail")
                    .whereEqualTo("type", "drink")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                CategoryItemDetail categoryItemDetail = documentSnapshot.toObject(CategoryItemDetail.class);
                                categoryItemDetailList.add(categoryItemDetail);
                                adapter.notifyDataSetChanged();
                            }
                        }

                    });
        }

//        ======getting sweet
        if(type != null && type.equalsIgnoreCase("sweet")){
            db.collection("CategoryItemDetail")
                    .whereEqualTo("type", "sweet")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                CategoryItemDetail categoryItemDetail = documentSnapshot.toObject(CategoryItemDetail.class);
                                categoryItemDetailList.add(categoryItemDetail);
                                adapter.notifyDataSetChanged();
                            }
                        }

                    });
        }
    }

    //comeback to previous page on action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}