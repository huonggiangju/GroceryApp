package com.example.groceryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.groceryapp.R;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
//        if(database.)

    }

    public void login(View view){
        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void registration(View view){
        Intent i = new Intent(HomeActivity.this, RegistrationActivity.class);
        startActivity(i);
    }
}