package com.nikhil.intelimentapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nikhil.intelimentapp.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Home ");

        ((LinearLayout) findViewById(R.id.llScenario1)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.llScenario2)).setOnClickListener(this);

        ((TextView) findViewById(R.id.tvScenario1)).setOnClickListener(this);
        ((TextView) findViewById(R.id.tvScenario1)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llScenario1:
            case R.id.tvScenario1:
                startActivity(new Intent(this, ScenarioOneActivity.class));
                break;

            case R.id.llScenario2:
            case R.id.tvScenario2:
                startActivity(new Intent(this, ScenarioTwoActivity.class));
                break;
        }

    }
}
