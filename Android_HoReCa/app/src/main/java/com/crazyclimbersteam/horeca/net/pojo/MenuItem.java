package com.crazyclimbersteam.horeca.net.pojo;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 05:51
 */
public class MenuItem {

    private String itemName;
    private String itemPrice;
    private String itemPortion;

    public MenuItem(String itemName, String itemPrice, String itemPortion) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemPortion = itemPortion;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemPortion() {
        return itemPortion;
    }

    public void setItemPortion(String itemPortion) {
        this.itemPortion = itemPortion;
    }
}
