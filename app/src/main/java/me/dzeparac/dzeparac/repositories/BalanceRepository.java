package me.dzeparac.dzeparac.repositories;

import android.util.Log;

import me.dzeparac.dzeparac.models.network.BalanceResponse;
import me.dzeparac.dzeparac.services.DzeparacApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BalanceRepository {
    public static final String TAG = "BalanceRepository";

    private double mBalance;
    private DzeparacApiService mApiService;

    public interface OnBalanceListener {
        void onBalance(double balance);
        void onBalanceError(String message);
    }

    public BalanceRepository(DzeparacApiService apiService) {
        this.mApiService = apiService;
    }

    public void getBalance(String email, final OnBalanceListener listener) {
        mApiService.getBalance(email).enqueue(new Callback<BalanceResponse>() {
            @Override
            public void onResponse(Call<BalanceResponse> call, Response<BalanceResponse> response) {
                if( response.isSuccessful() ) {
                    mBalance = response.body().amount;
                    listener.onBalance(mBalance);
                    return;
                }

                listener.onBalanceError("Greška");
            }

            @Override
            public void onFailure(Call<BalanceResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                listener.onBalanceError(t.getMessage());
            }
        });
    }
}
