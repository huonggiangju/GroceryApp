package com.example.groceryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.groceryapp.Adapters.ViewAllAdapter;
import com.example.groceryapp.Models.PopularModel;
import com.example.groceryapp.Models.ViewAllItem;
import com.example.groceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView viewAllRec;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllItem> viewAllItemList = new ArrayList<>();
    FirebaseFirestore db;
    Toolbar toolbar1;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        //getting intent from popular product
        String type = getIntent().getStringExtra("type");

        viewAllRec = findViewById(R.id.view_all_rec);
//        viewAllRec.setVisibility(View.GONE);
        viewAllRec.setLayoutManager(new LinearLayoutManager(this));
        viewAllAdapter = new ViewAllAdapter(this, viewAllItemList);
        viewAllRec.setAdapter(viewAllAdapter);

        //toolbar
        toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //progress bar
//        progressBar = (ProgressBar) findViewById(R.id.progressbar);
//        progressBar.setVisibility(View.VISIBLE);
        //connect DB
        db = FirebaseFirestore.getInstance();
//        //======getting fruit
        if(type != null && type.equalsIgnoreCase("fruit")){
            db.collection("AllProducts")
                    .whereEqualTo("type", "fruit")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllItem viewAllItem = documentSnapshot.toObject(ViewAllItem.class);
                        viewAllItemList.add(viewAllItem);
                        viewAllAdapter.notifyDataSetChanged();

//                        progressBar.setVisibility(View.GONE);
//                        viewAllRec.setVisibility(View.VISIBLE);
                    }
                }

            });
        }

        //======getting vegetable
        if(type != null && type.equalsIgnoreCase("vegetable")){
            db.collection("AllProducts")
                    .whereEqualTo("type", "vegetable")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                ViewAllItem viewAllItem = documentSnapshot.toObject(ViewAllItem.class);
                                viewAllItemList.add(viewAllItem);
                                viewAllAdapter.notifyDataSetChanged();

//                                progressBar.setVisibility(View.GONE);
//                                viewAllRec.setVisibility(View.VISIBLE);
                            }
                        }

                    });
        }

        //======getting fish
        if(type != null && type.equalsIgnoreCase("fish")){
            db.collection("AllProducts")
                    .whereEqualTo("type", "fish")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                ViewAllItem viewAllItem = documentSnapshot.toObject(ViewAllItem.class);
                                viewAllItemList.add(viewAllItem);
                                viewAllAdapter.notifyDataSetChanged();

//                                progressBar.setVisibility(View.GONE);
//                                viewAllRec.setVisibility(View.VISIBLE);
                            }
                        }

                    });
        }

        //======getting egg
        if(type != null && type.equalsIgnoreCase("egg")){
            db.collection("AllProducts")
                    .whereEqualTo("type", "egg")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                ViewAllItem viewAllItem = documentSnapshot.toObject(ViewAllItem.class);
                                viewAllItemList.add(viewAllItem);
                                viewAllAdapter.notifyDataSetChanged();

//                                progressBar.setVisibility(View.GONE);
//                                viewAllRec.setVisibility(View.VISIBLE);
                            }
                        }

                    });
        }
        //======getting egg
        if(type != null && type.equalsIgnoreCase("milk")){
            db.collection("AllProducts")
                    .whereEqualTo("type", "milk")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                ViewAllItem viewAllItem = documentSnapshot.toObject(ViewAllItem.class);
                                viewAllItemList.add(viewAllItem);
                                viewAllAdapter.notifyDataSetChanged();

//                                progressBar.setVisibility(View.GONE);
//                                viewAllRec.setVisibility(View.VISIBLE);
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