package me.dzeparac.dzeparac.services;


import java.util.List;

import me.dzeparac.dzeparac.models.Transaction;
import me.dzeparac.dzeparac.models.Wish;
import me.dzeparac.dzeparac.models.network.BalanceResponse;
import me.dzeparac.dzeparac.models.network.CreateTransactionRequest;
import me.dzeparac.dzeparac.models.network.CreateTransactionResponse;
import me.dzeparac.dzeparac.models.network.CreateWishRequest;
import me.dzeparac.dzeparac.models.network.CreateWishResponse;
import me.dzeparac.dzeparac.models.network.FulfillWishRequest;
import me.dzeparac.dzeparac.models.network.FulfillWishResponse;
import me.dzeparac.dzeparac.models.network.LoginRequest;
import me.dzeparac.dzeparac.models.network.LoginResponse;
import me.dzeparac.dzeparac.models.network.RegisterRequest;
import me.dzeparac.dzeparac.models.network.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DzeparacApiService {
    String BASE_URL = "http://192.168.8.121:52810";

    @POST("/user/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @GET("/user/balance")
    Call<BalanceResponse> getBalance(@Query("email") String email);

    @GET("/transaction/getAll")
    Call<List<Transaction>> getTransactions(@Query("email") String email);

    @GET("/wishlist/getAll")
    Call<List<Wish>> getWishes(@Query("email") String email);

    @POST("/transaction/insert")
    Call<CreateTransactionResponse> createTransaction(@Body CreateTransactionRequest transaction);

    @POST("/wishlist/insert")
    Call<CreateWishResponse> createWish(@Body CreateWishRequest wish);

    @POST("/wishlist/transaction")
    Call<FulfillWishResponse> fullfillWish(@Query("id") int wishId);
}
