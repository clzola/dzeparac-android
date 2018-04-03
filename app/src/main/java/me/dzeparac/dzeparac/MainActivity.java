package me.dzeparac.dzeparac;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dzeparac.dzeparac.adapters.MainPager;
import me.dzeparac.dzeparac.repositories.BalanceRepository;
import me.dzeparac.dzeparac.services.DzeparacApiService;
import me.dzeparac.dzeparac.ui.MyViewPager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.main_bottom_bar)
    BottomNavigationView mBottomBar;
    @BindView(R.id.main_view_pager)
    MyViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeViewPager();
    }

    private void initializeViewPager() {
        mViewPager.setAdapter(new MainPager(getSupportFragmentManager()));
        mViewPager.setPagingEnabled(false);
        mBottomBar.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    //----------------------------------------------------------------
    // Main Bottom Selected Listener
    //----------------------------------------------------------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_tab:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.action_history_tab:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.action_wishlist_tab:
                mViewPager.setCurrentItem(2);
                return true;
        }

        return false;
    }
}
