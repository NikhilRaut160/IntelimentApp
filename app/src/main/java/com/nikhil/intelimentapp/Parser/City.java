package com.nikhil.intelimentapp.Parser;

/**
 * Created by Nikhil on 09-03-2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class City {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fromcentral")
    @Expose
    private Fromcentral fromcentral;
    @SerializedName("location")
    @Expose
    private Location location;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The fromcentral
     */
    public Fromcentral getFromcentral() {
        return fromcentral;
    }

    /**
     *
     * @param fromcentral
     * The fromcentral
     */
    public void setFromcentral(Fromcentral fromcentral) {
        this.fromcentral = fromcentral;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}
