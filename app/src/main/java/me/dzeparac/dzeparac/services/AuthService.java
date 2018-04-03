package me.dzeparac.dzeparac.services;

import android.util.Log;

import com.google.gson.Gson;

import me.dzeparac.dzeparac.DzeparacApp;
import me.dzeparac.dzeparac.models.network.LoginRequest;
import me.dzeparac.dzeparac.models.network.LoginResponse;
import me.dzeparac.dzeparac.models.network.RegisterRequest;
import me.dzeparac.dzeparac.models.network.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by clzola on 11/18/17.
 */

public class AuthService {
    private static final String TAG = "AuthService";
    private final DzeparacApp mApplication;
    private DzeparacApiService mApiService;

    public interface OnLoginListener {
        void onLoginSuccess();
        void onLoginError(String message);
    }

    public interface OnRegisterListener {
        void onRegisterSuccess();
        void onRegisterError(String message);
    }

    public AuthService(DzeparacApp app, DzeparacApiService apiService) {
        this.mApiService = apiService;
        this.mApplication = app;
    }

    public void login(final String email, String password, final OnLoginListener listener) {
        this.mApiService.login(new LoginRequest(email, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, "onResponse()");
                if( response.isSuccessful() ) {
                    mApplication.saveEmail(email, response.body().id);
                    listener.onLoginSuccess();
                    return;
                }

                Log.d(TAG, "failed");
                listener.onLoginError("Greška");
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                listener.onLoginError(t.getMessage());
            }
        });
    }

//    public void register(final String email, String password, String name, final OnRegisterListener listener) {
//        this.mApiService.register(new RegisterRequest(email, password, name)).enqueue(new Callback<RegisterResponse>() {
//            @Override
//            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//                Log.d(TAG, "onResponse()");
//                if( response.isSuccessful() ) {
//                    mApplication.saveEmail(email);
//                    listener.onRegisterSuccess();
//                    return;
//                }
//
//                Log.d(TAG, "failed");
//                listener.onRegisterError("Greška");
//            }
//
//            @Override
//            public void onFailure(Call<RegisterResponse> call, Throwable t) {
//                Log.e(TAG, t.getMessage(), t);
//                listener.onRegisterError(t.getMessage());
//            }
//        });
//    }
}
