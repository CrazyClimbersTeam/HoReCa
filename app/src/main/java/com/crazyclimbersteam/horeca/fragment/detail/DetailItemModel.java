package com.crazyclimbersteam.horeca.fragment.detail;

/**
 * Created by Марковка on 21.02.2015.
 */
public class DetailItemModel {

    private String name;
    private float rating;
    private float distance;

    public DetailItemModel(String name, float rating, float distance) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
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
