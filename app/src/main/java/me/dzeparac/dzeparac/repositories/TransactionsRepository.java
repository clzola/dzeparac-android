package me.dzeparac.dzeparac.repositories;

import android.util.Log;

import java.util.List;

import me.dzeparac.dzeparac.models.Transaction;
import me.dzeparac.dzeparac.models.network.CreateTransactionRequest;
import me.dzeparac.dzeparac.models.network.CreateTransactionResponse;
import me.dzeparac.dzeparac.services.DzeparacApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by clzola on 11/18/17.
 */

public class TransactionsRepository {
    private static final String TAG = "TransactionsRepo";
    private DzeparacApiService mApiService;

    public interface OnTransactionsListener {
        void onTransactions(List<Transaction> transactions);
        void onTransactionsError(String message);
    }

    public interface OnCreateTransactionListener {
        void onTransactionCreated();
        void onTransactionCreatedError(String message);
    }

    public TransactionsRepository(DzeparacApiService apiService) {
        this.mApiService = apiService;
    }

    public void getAllTransactions(String email, final OnTransactionsListener listener) {
        this.mApiService.getTransactions(email).enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if( response.isSuccessful() ) {
                    listener.onTransactions(response.body());
                    return;
                }

                Log.d(TAG, "Failed");
                listener.onTransactionsError("Greška");
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                listener.onTransactionsError(t.getMessage());
            }
        });
    }

    public void create(int userId, String name, int categoryId, double amount, final OnCreateTransactionListener listener) {
        this.mApiService.createTransaction(new CreateTransactionRequest(name, categoryId, amount, userId)).enqueue(new Callback<CreateTransactionResponse>() {
            @Override
            public void onResponse(Call<CreateTransactionResponse> call, Response<CreateTransactionResponse> response) {
                if( response.isSuccessful() ) {
                    listener.onTransactionCreated();
                    return;
                }

                listener.onTransactionCreatedError("Greška");
            }

            @Override
            public void onFailure(Call<CreateTransactionResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                listener.onTransactionCreatedError(t.getMessage());
            }
        });
    }
}
