package com.crazyclimbersteam.horeca.activity.detail.fragment.feedback;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.crazyclimbersteam.horeca.HorecApplication;
import com.crazyclimbersteam.horeca.R;

/**
 * @author Mirash
 */
public class FeedbackItemModel {
    private String message;
    private String name;
    private float rating;
    private Drawable icon;

    public FeedbackItemModel(String message, String name, float rating, Drawable icon) {
        this.message = message;
        this.name = name;
        this.rating = rating;
        this.icon = icon;
    }

    public FeedbackItemModel() {
    }

    public FeedbackItemModel(String message, String name, float rating) {
        this(message, name, rating, HorecApplication.getInstance().getResources().getDrawable(R.drawable.ferret));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
