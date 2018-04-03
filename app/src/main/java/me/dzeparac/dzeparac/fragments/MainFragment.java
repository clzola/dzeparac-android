package me.dzeparac.dzeparac.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dzeparac.dzeparac.DzeparacApp;
import me.dzeparac.dzeparac.R;
import me.dzeparac.dzeparac.repositories.BalanceRepository;


public class MainFragment extends Fragment implements BalanceRepository.OnBalanceListener {

    public static final String TAG = "MainFragment";

    @BindView(R.id.tv_balance_display) TextView mBalanceDisplay;

    private ViewGroup mRoot;
    private DzeparacApp mApp;
    private BalanceRepository mBalanceRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = (ViewGroup) inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, mRoot);
        mApp = (DzeparacApp) getActivity().getApplication();
        mBalanceRepository = mApp.getBalanceRepository();
        return mRoot;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBalanceRepository.getBalance(mApp.getSavedEmail(), this);
        Log.d(TAG, "onStart");
    }

    @Override
    public void onBalance(double balance) {
        mBalanceDisplay.setText(getString(R.string.balance_display, balance));
        mBalanceDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBalanceError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
