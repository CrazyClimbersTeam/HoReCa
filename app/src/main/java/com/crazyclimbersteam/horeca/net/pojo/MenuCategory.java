package com.crazyclimbersteam.horeca.net.pojo;

import java.util.ArrayList;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 05:52
 */
public class MenuCategory {

    private String categoryName;
    private ArrayList<MenuItem> itemList;

    public MenuCategory(String categoryName, ArrayList<MenuItem> itemList) {
        this.categoryName = categoryName;
        this.itemList = itemList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<MenuItem> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<MenuItem> itemList) {
        this.itemList = itemList;
    }
}
