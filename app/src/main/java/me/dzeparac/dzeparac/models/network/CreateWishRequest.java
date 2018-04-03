package me.dzeparac.dzeparac.models.network;

/**
 * Created by clzola on 11/18/17.
 */

public class CreateWishRequest {
    public String name;
    public int categoryId;
    public double amount;
    public int userId;

    public CreateWishRequest(String name, int categoryId, double amount, int userId) {
        this.name = name;
        this.categoryId = categoryId;
        this.amount = amount;
        this.userId = userId;
    }
}
