package com.nikhil.intelimentapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikhil.intelimentapp.Database.DBHelper;
import com.nikhil.intelimentapp.Model.CityModel;
import com.nikhil.intelimentapp.Parser.AsyncTaskCompleteListener;
import com.nikhil.intelimentapp.Parser.City;
import com.nikhil.intelimentapp.Parser.HttpRequester;
import com.nikhil.intelimentapp.R;
import com.nikhil.intelimentapp.Utility.Constants;
import com.nikhil.intelimentapp.Utility.PreferencesManger;
import com.nikhil.intelimentapp.Utility.UIUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScenarioTwoActivity extends AppCompatActivity implements AsyncTaskCompleteListener {

    private Spinner citySpinner;
    private TextView tvCar, tvTrain;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> cityList;
    private String selectCity;
    private DBHelper dbHelper;
    private CityModel city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_two);
        dbHelper = new DBHelper(this);
//        if (PreferencesManger.getBooleanFields(this, Constants.Preferences.IS_DATA_DOWNLOAD)) {
        bindView();
        bindData();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!PreferencesManger.getBooleanFields(this, Constants.Preferences.IS_DATA_DOWNLOAD)) {
            if (UIUtil.isInternetAvailable(this)) {
                fetchDataFromServer();
            } else {
                showSettingAlert();
            }
        }

    }

    private void bindView() {

        tvCar = (TextView) findViewById(R.id.tvCar);
        tvTrain = (TextView) findViewById(R.id.tvTrain);
        citySpinner = (Spinner) findViewById(R.id.spinnerCity);

        cityList = new ArrayList<>();
        cityList.clear();
        cityList.add("Select city");
        if (dbHelper.getAllCites() != null)
            cityList.addAll(dbHelper.getAllCites());
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (PreferencesManger.getBooleanFields(ScenarioTwoActivity.this, Constants.Preferences.IS_DATA_DOWNLOAD)) {
                    if (position != 0) {
                        selectCity = cityList.get(position);
                    } else {
                        selectCity = "";
                    }
                    bindData();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ((Button) findViewById(R.id.btnNavigation)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (city != null) {
                    UIUtil.ShowToast(ScenarioTwoActivity.this, "Lat -- " + city.getLatitude() + "  Long - " + city.getLongitude());
                    Intent intent = new Intent(ScenarioTwoActivity.this, CityMapLocationActivity.class);
                    intent.putExtra(Constants.CITY, city);
                    startActivity(intent);
                } else {
                    UIUtil.ShowToast(ScenarioTwoActivity.this, "Please Select city.");
                }
            }
        });
    }

    private void bindData() {
        if (PreferencesManger.getBooleanFields(this, Constants.Preferences.IS_DATA_DOWNLOAD)) {
            if (TextUtils.isEmpty(selectCity)) {
                tvCar.setText("Car - ");
                tvTrain.setText("Train - ");
                UIUtil.ShowToast(this, "Please select City.");
                city = null;
                return;
            } else {
                city = dbHelper.getCityModelFromCityName(selectCity);
                if (city != null) {
                    tvCar.setText("Car - " + city.getCarMode());
                    if (!TextUtils.isEmpty(city.getTrainMode())) {
                        tvTrain.setText("Train - " + city.getTrainMode());
                    } else {
                        tvTrain.setText("Train - Not Avilale");
                    }
                }
            }
        } else {
            fetchDataFromServer();
        }
    }


    private void showSettingAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Internet");
        builder.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void fetchDataFromServer() {
        UIUtil.startProgressDialog(this, "Please wait.. Data downloading..!");
        new HttpRequester(Constants.Host, this);
    }

    @Override
    public void onTaskCompleted(String response) {
        UIUtil.stopProgressDialog(this);
        if (TextUtils.isEmpty(response)) {
            UIUtil.ShowToastCenter(this, "Something went wrong, Server not Server not responding!");
        } else {

            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                Gson gson = gsonBuilder.create();
                List<City> cityList = new ArrayList<City>();
                cityList = Arrays.asList(gson.fromJson(response.toString(), City[].class));
                dbHelper.insertCities(cityList);
                PreferencesManger.addBooleanFields(this, Constants.Preferences.IS_DATA_DOWNLOAD, true);
                bindView();
                bindData();

            } catch (Exception e) {
                e.printStackTrace();
                UIUtil.AppErrorLog(e.toString());
            }

        }

    }
}
