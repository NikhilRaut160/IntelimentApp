package com.nikhil.intelimentapp.Model;

import java.io.Serializable;

/**
 * Created by Nikhil on 09-03-2016.
 */
public class CityModel implements Serializable {
    private int id;
    private String name, carMode, trainMode, latitude, longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarMode() {
        return carMode;
    }

    public void setCarMode(String carMode) {
        this.carMode = carMode;
    }

    public String getTrainMode() {
        return trainMode;
    }

    public void setTrainMode(String trainMode) {
        this.trainMode = trainMode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carMode='" + carMode + '\'' +
                ", trainMode='" + trainMode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
