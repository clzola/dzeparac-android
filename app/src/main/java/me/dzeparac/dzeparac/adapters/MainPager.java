package me.dzeparac.dzeparac.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.dzeparac.dzeparac.fragments.HistoryFragment;
import me.dzeparac.dzeparac.fragments.MainFragment;
import me.dzeparac.dzeparac.fragments.WishListFragment;


public class MainPager extends FragmentStatePagerAdapter {
    public static final String TAG = "MainPager";

    public MainPager(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new HistoryFragment();
            case 2:
                return new WishListFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
