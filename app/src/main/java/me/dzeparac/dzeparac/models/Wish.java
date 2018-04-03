package me.dzeparac.dzeparac.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by clzola on 11/18/17.
 */

public class Wish {
    @SerializedName("Id") public int id;
    @SerializedName("Name") public String name;
    @SerializedName("Amount") public double amount;
    @SerializedName("Category") public Category category;
}
