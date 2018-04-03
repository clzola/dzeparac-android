package me.dzeparac.dzeparac.models;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

/**
 * Created by clzola on 11/18/17.
 */

public class Transaction {
    @SerializedName("Id") public int id;
    @SerializedName("CreatedAt") public String createdAt;
    @SerializedName("Name") public String name;
    @SerializedName("Category") public Category category;
    @SerializedName("Amount") public double amount;

    public static String[] MONTHS = {
            "Januar",
            "Februar",
            "Mart",
            "April",
            "Maj",
            "Jun",
            "Jul",
            "Avgust",
            "Septembar",
            "Oktovar",
            "Novembar",
            "Decembar"
    };

    public String getCreatedAtHumanString() {
        String[] parts = createdAt.split("T");
        String[] date = parts[0].split("-");
        String[] time = parts[1].split(":");

        int month = Integer.parseInt(date[1]);

        return String.format("%s. %s %s %s:%s",
                date[2],
                MONTHS[month-1],
                date[0],
                time[0],
                time[1]
        );
    }
}
