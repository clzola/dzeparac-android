package me.dzeparac.dzeparac.models.network;

/**
 * Created by clzola on 11/18/17.
 */

public class RegisterRequest {
    public String email;
    public String password;
    public String name;

    public RegisterRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
