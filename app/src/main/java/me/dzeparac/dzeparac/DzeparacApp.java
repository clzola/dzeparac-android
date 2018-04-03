package me.dzeparac.dzeparac;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.dzeparac.dzeparac.repositories.BalanceRepository;
import me.dzeparac.dzeparac.repositories.CategoriesRepository;
import me.dzeparac.dzeparac.repositories.TransactionsRepository;
import me.dzeparac.dzeparac.repositories.WishesRepository;
import me.dzeparac.dzeparac.services.AuthService;
import me.dzeparac.dzeparac.services.DzeparacApiService;
import me.dzeparac.dzeparac.utils.DzeparacMockInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DzeparacApp extends Application {

    public static final String TAG = "DzeparacApp";
    public static final String PREF_SAVED_EMAIL = "pref_saved_email";

    private String mEmail;
    private int mId;
    private DzeparacApiService mApiService;
    private AuthService mAuthService;
    private BalanceRepository mBalanceRepository;
    private TransactionsRepository mTransactionsRepository;
    private WishesRepository mWishesRepository;
    private CategoriesRepository mCategoriesRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        this.loadSavedEmailFromPref();
        this.createApiService();
        this.createServices();
        this.createRepositories();
    }

    private void loadSavedEmailFromPref() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        mEmail = pref.getString(PREF_SAVED_EMAIL, null);
    }

    private void createApiService() {
        OkHttpClient client = new OkHttpClient.Builder()
                                .addInterceptor(new DzeparacMockInterceptor())
                                .build();

        Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(DzeparacApiService.BASE_URL)
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        mApiService = retrofit.create(DzeparacApiService.class);
    }

    private void createServices() {
        this.mAuthService = new AuthService(this, mApiService);
    }

    private void createRepositories() {
        mBalanceRepository = new BalanceRepository(mApiService);
        mTransactionsRepository = new TransactionsRepository(mApiService);
        mWishesRepository = new WishesRepository(mApiService);
        mCategoriesRepository = new CategoriesRepository();
    }

    public String getSavedEmail() {
        return this.mEmail;
    }

    public int getId() {
        return this.mId;
    }

    public DzeparacApiService getApiService() {
        return mApiService;
    }

    public AuthService getAuthService() {
        return mAuthService;
    }

    public BalanceRepository getBalanceRepository() {
        return mBalanceRepository;
    }

    public TransactionsRepository getTransactionsRepository() {
        return mTransactionsRepository;
    }

    public WishesRepository getWishesRepository() {
        return mWishesRepository;
    }

    public CategoriesRepository getCategoriesRepository() {
        return mCategoriesRepository;
    }

    public void saveEmail(String email, int id) {
        mEmail = email;
        mId = id;
    }
}
