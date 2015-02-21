package com.crazyclimbersteam.horeca.net.pojo;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 21.02.15
 * Time: 14:46
 */
public class Category {

    private String imageUrl;
    private String name;

    public Category(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
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
