package com.example.andre.budget;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class TabFlex extends Fragment {
    CircularProgressBar circularProgressBar2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabflex, container, false);
        circularProgressBar2 = (CircularProgressBar)rootView.findViewById(R.id.flexBar);
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar2.setProgressWithAnimation(45, animationDuration);
        return rootView;
    }
}
