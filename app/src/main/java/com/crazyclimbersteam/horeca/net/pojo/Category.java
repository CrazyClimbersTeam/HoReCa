package com.crazyclimbersteam.horeca.net.pojo;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 21.02.15
 * Time: 14:46
 */
public class Category {
    private static final int NO_ID = 0;

    private String imageUrl;
    private String name;
    private int defaultImageId = NO_ID;

    public Category(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public Category(String imageUrl, String name, int defaultImageId) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.defaultImageId = defaultImageId;
    }

    public Category(String name, int defaultImageId) {
        this("url", name, defaultImageId);
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public int getDefaultImageId() {
        return defaultImageId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
