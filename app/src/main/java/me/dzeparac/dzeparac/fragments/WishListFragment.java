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
import me.dzeparac.dzeparac.AddWishActivity;
import me.dzeparac.dzeparac.DzeparacApp;
import me.dzeparac.dzeparac.R;
import me.dzeparac.dzeparac.adapters.TransactionsRecyclerAdapter;
import me.dzeparac.dzeparac.adapters.WishRecyclerAdapter;
import me.dzeparac.dzeparac.models.Transaction;
import me.dzeparac.dzeparac.models.Wish;
import me.dzeparac.dzeparac.repositories.TransactionsRepository;
import me.dzeparac.dzeparac.repositories.WishesRepository;

/**
 * Created by clzola on 11/18/17.
 */

public class WishListFragment extends Fragment implements WishesRepository.OnWishesListener, WishRecyclerAdapter.WishItemListener, WishesRepository.OnFullfillWishListener {

    @BindView(R.id.wishes_list)
    RecyclerView mWishesList;

    private ViewGroup mRoot;
    private DzeparacApp mApp;
    private WishesRepository mWishesRepository;
    private WishRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = (ViewGroup) inflater.inflate(R.layout.fragment_wishlist, null);
        ButterKnife.bind(this, mRoot);
        mApp = (DzeparacApp) getActivity().getApplication();
        mWishesRepository = mApp.getWishesRepository();
        initializeRecyclerView();
        setHasOptionsMenu(true);
        return mRoot;
    }

    private void initializeRecyclerView() {
        mAdapter = new WishRecyclerAdapter();
        mWishesList.setAdapter(mAdapter);
        mWishesList.setHasFixedSize(true);
        mWishesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setWishItemListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mWishesRepository.getAllWishes(mApp.getSavedEmail(), this);
    }

    @Override
    public void onWishes(List<Wish> wishes) {
        ArrayList<Wish> transactions1 = new ArrayList<>(wishes);
        mAdapter.setData(transactions1);
    }


    @Override
    public void onWishesError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_wishes, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.action_add_wish ) {
            startActivity(new Intent(getActivity(), AddWishActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBuyWishButtonClick(Wish wish) {
        this.mWishesRepository.fullfill(wish.id, this);
    }

    @Override
    public void onFullfillWishSuccess() {
        mWishesRepository.getAllWishes(mApp.getSavedEmail(), this);
    }

    @Override
    public void onFullfillWishError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
