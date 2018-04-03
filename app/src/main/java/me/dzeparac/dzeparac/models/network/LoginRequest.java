package me.dzeparac.dzeparac.models.network;

/**
 * Created by clzola on 11/18/17.
 */

public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
