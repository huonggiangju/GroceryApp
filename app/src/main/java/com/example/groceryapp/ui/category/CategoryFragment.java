package com.example.groceryapp.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.Adapters.CategoryItemAdapter;
import com.example.groceryapp.Models.CategoryItem;
import com.example.groceryapp.Models.PopularModel;
import com.example.groceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    FirebaseFirestore db;
    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView catItem_rec;
    List<CategoryItem> categoryItemList = new ArrayList<>();
    CategoryItemAdapter categoryItemAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_category, container, false);

        //setting adapter
        catItem_rec = root.findViewById(R.id.cat_rec);
        catItem_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        categoryItemAdapter = new CategoryItemAdapter(getActivity(), categoryItemList);
        catItem_rec.setAdapter(categoryItemAdapter);

        //connect DB
        db = FirebaseFirestore.getInstance();
        db.collection("CategoryItem")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryItem categoryItem = document.toObject(CategoryItem.class);
                                categoryItemList.add(categoryItem);
                                categoryItemAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}