package me.dzeparac.dzeparac.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MyViewPager extends ViewPager {
    private boolean mIsEnabled;

    public MyViewPager(Context context) {
        super(context);
        mIsEnabled = true;
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mIsEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mIsEnabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.mIsEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.mIsEnabled = enabled;
    }
}
