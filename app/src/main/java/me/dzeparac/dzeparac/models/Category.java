package me.dzeparac.dzeparac.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by clzola on 11/18/17.
 */

public class Category {
    @SerializedName("Id") public int id;
    @SerializedName("Name") public String name;
    @SerializedName("Icon") public String iconUrl;

    public Category() {

    }

    public Category(int id, String name, String iconUrl) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
