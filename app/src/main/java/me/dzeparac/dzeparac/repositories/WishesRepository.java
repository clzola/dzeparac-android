package me.dzeparac.dzeparac.repositories;

import android.util.Log;

import java.util.List;

import me.dzeparac.dzeparac.models.Transaction;
import me.dzeparac.dzeparac.models.Wish;
import me.dzeparac.dzeparac.models.network.CreateWishRequest;
import me.dzeparac.dzeparac.models.network.CreateWishResponse;
import me.dzeparac.dzeparac.models.network.FulfillWishRequest;
import me.dzeparac.dzeparac.models.network.FulfillWishResponse;
import me.dzeparac.dzeparac.services.DzeparacApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by clzola on 11/18/17.
 */

public class WishesRepository {
    private static final String TAG = "WishesRepo";
    private DzeparacApiService mApiService;

    public interface OnWishesListener {
        void onWishes(List<Wish> wishes);
        void onWishesError(String message);
    }

    public interface OnCreateWishListener {
        void onWishCreated();
        void onWishCreatedError(String message);
    }

    public interface OnFullfillWishListener {
        void onFullfillWishSuccess();
        void onFullfillWishError(String message);
    }

    public WishesRepository(DzeparacApiService apiService) {
        this.mApiService = apiService;
    }

    public void getAllWishes(String email, final OnWishesListener listener) {
        this.mApiService.getWishes(email).enqueue(new Callback<List<Wish>>() {
            @Override
            public void onResponse(Call<List<Wish>> call, Response<List<Wish>> response) {
                if( response.isSuccessful() ) {
                    listener.onWishes(response.body());
                    return;
                }

                Log.d(TAG, "Failed");
                listener.onWishesError("Greška");
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                listener.onWishesError(t.getMessage());
            }
        });
    }

    public void create(int userId, String name, int categoryId, double amount, final OnCreateWishListener listener) {
        this.mApiService.createWish(new CreateWishRequest(name, categoryId, amount, userId)).enqueue(new Callback<CreateWishResponse>() {
            @Override
            public void onResponse(Call<CreateWishResponse> call, Response<CreateWishResponse> response) {
                if( response.isSuccessful() ) {
                    listener.onWishCreated();
                    return;
                }

                listener.onWishCreatedError("Greška");
            }

            @Override
            public void onFailure(Call<CreateWishResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                listener.onWishCreatedError(t.getMessage());
            }
        });
    }

    public void fullfill(int id, final OnFullfillWishListener listener) {
        this.mApiService.fullfillWish(id).enqueue(new Callback<FulfillWishResponse>() {
            @Override
            public void onResponse(Call<FulfillWishResponse> call, Response<FulfillWishResponse> response) {
                if( response.isSuccessful() ) {
                    listener.onFullfillWishSuccess();
                    return;
                }

                listener.onFullfillWishError("Greška");
            }

            @Override
            public void onFailure(Call<FulfillWishResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage(),t);
                listener.onFullfillWishError(t.getMessage());
            }
        });
    }
}
