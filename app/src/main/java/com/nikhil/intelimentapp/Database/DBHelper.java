package com.nikhil.intelimentapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nikhil.intelimentapp.Model.CityModel;
import com.nikhil.intelimentapp.Parser.City;
import com.nikhil.intelimentapp.Utility.Constants;
import com.nikhil.intelimentapp.Utility.PreferencesManger;
import com.nikhil.intelimentapp.Utility.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikhil on 09-03-2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "app_db";

    //Model Table Name

    public static final String TABLE_CITY = "city";

    //Model Table Columns
    private static final String KEY_CITY_ID = "city_id";
    private static final String KEY_CITY_NAME = "city_name";
    private static final String KEY_TRANSPORT_MODE_CAR = "transport_mode_car";
    private static final String KEY_TRANSPORT_MODE_TRAIN = "transport_mode_tran";
    private static final String KEY_CITY_LATITUDE = "city_latitude";
    private static final String KEY_CITY_LONGITUDE = "city_longitude";
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CITY = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_CITY_NAME + " TEXT,"
                + KEY_TRANSPORT_MODE_CAR + " TEXT,"
                + KEY_TRANSPORT_MODE_TRAIN + " TEXT, "
                + KEY_CITY_LATITUDE + " TEXT,"
                + KEY_CITY_LONGITUDE + " TEXT " + ");";
        db.execSQL(CREATE_TABLE_CITY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertCities(List<City> cityList) {
        try {


            if (cityList != null) {
                deleteAllCities();
                PreferencesManger.addBooleanFields(context, Constants.Preferences.IS_DATA_DOWNLOAD, false);
            }

            for (int i = 0; i < cityList.size(); i++) {
                SQLiteDatabase db = this.getWritableDatabase();
                City city = cityList.get(i);
                ContentValues values = new ContentValues();
                values.put(KEY_CITY_ID, city.getId());
                values.put(KEY_CITY_NAME, city.getName());
                values.put(KEY_TRANSPORT_MODE_CAR, city.getFromcentral().getCar());
                values.put(KEY_TRANSPORT_MODE_TRAIN, city.getFromcentral().getTrain());
                values.put(KEY_CITY_LATITUDE, city.getLocation().getLatitude());
                values.put(KEY_CITY_LONGITUDE, city.getLocation().getLongitude());
                db.insert(TABLE_CITY, null, values);
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            UIUtil.AppErrorLog("Insert Cities DB Exception - " + e);
        }
    }


    public ArrayList<String> getAllCites() {
        try {
            ArrayList<String> cityList = new ArrayList<String>();
            String selectQuery = "SELECT " + KEY_CITY_NAME + " FROM " + TABLE_CITY + " ORDER BY " + KEY_CITY_NAME + " COLLATE NOCASE ASC ";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    cityList.add(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY_NAME)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return cityList;
        } catch (Exception e) {
            e.printStackTrace();
            UIUtil.AppErrorLog("GetAllCites DB Exception - " + e.toString());
            return null;
        }

    }

    public CityModel getCityModelFromCityName(String cityName) {
        try {

            String selectQuery = "SELECT * FROM " + TABLE_CITY + " WHERE " + KEY_CITY_NAME + " ='" + cityName + "'";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            CityModel city = null;
            if (cursor.moveToFirst()) {
                do {
                    city = new CityModel();
                    city.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CITY_ID)));
                    city.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY_NAME)));
                    city.setCarMode(cursor.getString(cursor.getColumnIndexOrThrow(KEY_TRANSPORT_MODE_CAR)));
                    city.setTrainMode(cursor.getString(cursor.getColumnIndexOrThrow(KEY_TRANSPORT_MODE_TRAIN)));
                    city.setLatitude(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY_LATITUDE)));
                    city.setLongitude(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY_LONGITUDE)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return city;
        } catch (Exception e) {
            e.printStackTrace();
            UIUtil.AppErrorLog("GetAllCites DB Exception - " + e.toString());
            return null;
        }

    }

    public void deleteAllCities() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CITY, null, null);
        db.close();
    }

}
