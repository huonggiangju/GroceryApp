package com.example.groceryapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.Adapters.HomeCategoryAdapter;
import com.example.groceryapp.Adapters.PopularAdapter;
import com.example.groceryapp.Adapters.RecommendedAdapter;
import com.example.groceryapp.Adapters.ViewAllAdapter;
import com.example.groceryapp.Models.HomeCategory;
import com.example.groceryapp.Models.PopularModel;
import com.example.groceryapp.Models.Recommended;
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

public class HomeFragment extends Fragment {


    FirebaseFirestore db;
    ScrollView scrollView;
    ProgressBar progressBar;

    //popular item
    List<PopularModel> popularModelList = new ArrayList<>();
    PopularAdapter popularAdapter;
    RecyclerView popularRecycleView;

    //home category item
    List<HomeCategory> homeCategoryList = new ArrayList<>();
    HomeCategoryAdapter homeCategoryAdapter;
    RecyclerView homeCatRecycleView;

    //recommended item
    List<Recommended> recommendedList = new ArrayList<>();
    RecommendedAdapter recommendedAdapter;
    RecyclerView recommendedRecycleView;

    //search box
    EditText searchBox;
    private RecyclerView searchRec;
    private List<ViewAllItem> viewAllItemList = new ArrayList<>();
    private ViewAllAdapter viewAllAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        //==============popular items
        //setting adapter
        popularRecycleView = root.findViewById(R.id.pop_rec);
        popularRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularAdapter = new PopularAdapter(getActivity(), popularModelList);
        popularRecycleView.setAdapter(popularAdapter);
        popularAdapter.notifyDataSetChanged();

        //connect DB
        db = FirebaseFirestore.getInstance();
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //====================home category item
        homeCatRecycleView = root.findViewById(R.id.home_cat_rec);
        homeCatRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeCategoryAdapter = new HomeCategoryAdapter(getActivity(), homeCategoryList);
        homeCatRecycleView.setAdapter(homeCategoryAdapter);
        //connect DB
        db = FirebaseFirestore.getInstance();
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                homeCategoryList.add(homeCategory);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //====================recommended item
        recommendedRecycleView = root.findViewById(R.id.recomment_rec);
        recommendedRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendedAdapter = new RecommendedAdapter(getActivity(), recommendedList);
        recommendedRecycleView.setAdapter(recommendedAdapter);
        //connect DB
        db = FirebaseFirestore.getInstance();
        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Recommended recommended = document.toObject(Recommended.class);
                                recommendedList.add(recommended);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        ===============SearchBox
        searchBox = root.findViewById(R.id.search_box);
        searchRec = root.findViewById(R.id.search_rec);
        searchRec.setLayoutManager(new LinearLayoutManager(getContext()));

        viewAllAdapter = new ViewAllAdapter(getActivity(), viewAllItemList );
        searchRec.setAdapter(viewAllAdapter);
        searchRec.setHasFixedSize(true);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable e) {

                if(e.toString().isEmpty()){
                    viewAllItemList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else{
                    searchProduct(e.toString());
                }
            }
        });

        return root;
    }

    private void searchProduct(String type) {
        if(!type.isEmpty()){
            db.collection("AllProducts")
                    .whereEqualTo("type", type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful() && task.getResult() != null){
                                viewAllItemList.clear();
                                viewAllAdapter.notifyDataSetChanged();

                                for(DocumentSnapshot doc: task.getResult().getDocuments()){
                                    ViewAllItem viewAllItem = doc.toObject(ViewAllItem.class);
                                    viewAllItemList.add(viewAllItem);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

        }
    }
}