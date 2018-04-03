package me.dzeparac.dzeparac.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dzeparac.dzeparac.AddTransactionActivity;
import me.dzeparac.dzeparac.DzeparacApp;
import me.dzeparac.dzeparac.R;
import me.dzeparac.dzeparac.adapters.TransactionsRecyclerAdapter;
import me.dzeparac.dzeparac.models.Transaction;
import me.dzeparac.dzeparac.repositories.TransactionsRepository;


public class HistoryFragment extends Fragment implements TransactionsRepository.OnTransactionsListener {

    @BindView(R.id.transactions_list) RecyclerView mTransactionsList;

    private ViewGroup mRoot;
    private DzeparacApp mApp;
    private TransactionsRepository mTransactionsRepository;
    private TransactionsRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = (ViewGroup) inflater.inflate(R.layout.fragment_history, null);
        ButterKnife.bind(this, mRoot);
        mApp = (DzeparacApp) getActivity().getApplication();
        mTransactionsRepository = mApp.getTransactionsRepository();
        initializeRecyclerView();
        setHasOptionsMenu(true);
        return mRoot;
    }

    private void initializeRecyclerView() {
        mAdapter = new TransactionsRecyclerAdapter();
        mTransactionsList.setAdapter(mAdapter);
        mTransactionsList.setHasFixedSize(true);
        mTransactionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();
        mTransactionsRepository.getAllTransactions(mApp.getSavedEmail(), this);
    }

    @Override
    public void onTransactions(List<Transaction> transactions) {
        ArrayList<Transaction> transactions1 = new ArrayList<>(transactions);
        mAdapter.setData(transactions1);
    }

    @Override
    public void onTransactionsError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_history, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(  item.getItemId() == R.id.action_add_transaction) {
            startActivity(new Intent(getActivity(), AddTransactionActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
