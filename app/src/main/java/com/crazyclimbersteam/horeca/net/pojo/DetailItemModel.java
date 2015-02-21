package com.crazyclimbersteam.horeca.net.pojo;

/**
 * Created by Марковка on 21.02.2015.
 */
public class DetailItemModel {

    private String id;
    private String name;
    private String address;
    private float rating;
    private float distance;
    private String lng;
    private String lat;

    public DetailItemModel(String name, float rating, float distance) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
