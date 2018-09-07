package com.example.andre.budget;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TabFlex extends Fragment {
    CircularProgressBar circularProgressBar2;
    //a list to store all the products
    List<CostEntry> productList;


    //
    private RecyclerView mRecyclerView;
    private CEAdapter mAdapter;
    private List<CostEntryDetails> costEntries;

    //
    //the recyclerview
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabflex, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        getGenres();
        mAdapter = new CEAdapter(costEntries);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(mAdapter);

//        circularProgressBar2 = (CircularProgressBar)rootView.findViewById(R.id.flexBar);
//        int animationDuration = 2500; // 2500ms = 2,5s
//        circularProgressBar2.setProgressWithAnimation(45, animationDuration);
        //getting the recyclerview from xml

//        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
//
//        //initializing the productlist
//        productList = new ArrayList<>();
//
//
//        //adding some items to our list
//        productList.add(
//                new CostEntry(
//                        new Date(1000),
//                        2,
//                        "13.3 inch, Silver, 1.35 kg",
//                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)"));
//
//        productList.add(
//                new CostEntry(
//                        new Date(1000),
//                        2,
//                        "13.3 inch, Silver, 1.35 kg",
//                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)"));
//
//        productList.add(
//                new CostEntry(
//                        new Date(1000),
//                        2,
//                        "13.3 inch, Silver, 1.35 kg",
//                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)"));
//        productList.add(
//                new CostEntry(
//                        new Date(1000),
//                        2,
//                        "13.3 inch, Silver, 1.35 kg",
//                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)"));
//
//        productList.add(
//                new CostEntry(
//                        new Date(1000),
//                        2,
//                        "13.3 inch, Silver, 1.35 kg",
//                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)"));
//
//        productList.add(
//                new CostEntry(
//                        new Date(1000),
//                        2,
//                        "13.3 inch, Silver, 1.35 kg",
//                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)"));
//
//        //creating recyclerview adapter
//        CostEntryAdapter adapter = new CostEntryAdapter(container.getContext(), productList);
//
//        //setting adapter to recyclerview
//        recyclerView.setAdapter(adapter);



        return rootView;
    }

    public void getGenres() {
        costEntries = new ArrayList<>(6);
        for (int i =0;i < 6; i++)
        {
            List<CostEntry> costen = new ArrayList<>(3);
            costen.add(new CostEntry("test1"));
            costen.add(new CostEntry("test2"));
            costen.add(new CostEntry("test3"));
            costEntries.add(new CostEntryDetails("header_" + i ,costen));

        }

    }
}
