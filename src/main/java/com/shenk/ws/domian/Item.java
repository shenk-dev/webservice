package com.shenk.ws.domian;

public class Item {

    private String itemCode;

    private String itemValue;

    public Item(){}

    public Item(String itemCode, String itemValue) {
        this.itemCode = itemCode;
        this.itemValue = itemValue;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
