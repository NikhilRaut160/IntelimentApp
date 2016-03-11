package com.nikhil.intelimentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.matthewtamlin.sliding_intro_screen_library.SelectionIndicator;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SelectionIndicator selectInd = (SelectionIndicator) findViewById(R.id.selectInd);
        selectInd.setActiveDotColor(R.color.colorPrimary);
        selectInd.setActiveItem(1, true);
    }
}
