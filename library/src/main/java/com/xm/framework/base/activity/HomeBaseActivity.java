package com.xm.framework.base.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.xm.framework.R;
import com.xm.framework.base.BaseActivity;
import com.xm.framework.buttondrawable.DrawableManager;
import com.xm.framework.tools.AndroidTools;
import com.xm.framework.tools.ExitTools;
import com.xm.framework.view.viewpaper.PagerAdapter;
import com.xm.framework.view.viewpaper.XmViewPager;

/**
 * @author Alan
 * 时 间：2019-10-29
 * 简 述：主界面基类
 */
public abstract class HomeBaseActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    protected View[] mMsgHitView;
    protected BottomNavigationView mBottomNavigationView;
    protected XmViewPager mViewPager;
    protected PagerAdapter<Fragment> mPagerAdapter;

    @Override
    protected void initView() {
        mBottomNavigationView = findViewById(R.id.bv_home_navigation);
        mViewPager = findViewById(R.id.vp_home_pager);
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mBottomNavigationView.setItemHorizontalTranslationEnabled(true);
        mMsgHitView = new View[getTabSize()];
        initViewPager();
    }

    protected void initViewPager() {
        FragmentManager fm = getSupportFragmentManager();
        mPagerAdapter = new PagerAdapter<>(fm, getFragmentArray());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setScrollable(false);
        mViewPager.setOffscreenPageLimit(getTabSize());
        mViewPager.setCurrentItem(0, false);
        mViewPager.addOnPageChangeListener(this);
    }

    protected abstract Fragment[] getFragmentArray();

    @Override
    protected int getContentId() {
        return R.layout.activity_home_base;
    }

    public void showMsgHitView(int position) {
        if (position < mMsgHitView.length) {
            if (mMsgHitView[position] == null) {
                generateMsgHitView(position);
            } else {
                mMsgHitView[position].setVisibility(View.VISIBLE);
            }
        }
    }

    public void hiddenHitMsgView(int position) {
        if (position < mMsgHitView.length && mMsgHitView[position] != null) {
            mMsgHitView[position].setVisibility(View.GONE);
        }
    }

    protected int getTabSize() {
        return mBottomNavigationView.getMenu().size();
    }

    private void generateMsgHitView(int position) {
        mBottomNavigationView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mBottomNavigationView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mBottomNavigationView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                View view = new View(getActivity());
                view.setBackgroundDrawable(generateMsgHitViewDrawable());
                view.setLayoutParams(generateMsgHitViewLayoutParams(position));
                mBottomNavigationView.addView(view);
            }
        });
    }

    protected Drawable generateMsgHitViewDrawable() {
        Drawable shapeDrawable = DrawableManager.getShapeDrawable(Color.RED, AndroidTools.dip2px(this, 6));
        return shapeDrawable;
    }

    protected FrameLayout.LayoutParams generateMsgHitViewLayoutParams(int position) {
        int i = AndroidTools.dip2px(this, 8);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i);
        int[] screenSize = AndroidTools.getScreenSize(this);
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) mBottomNavigationView.getChildAt(0);
        BottomNavigationItemView v = (BottomNavigationItemView) bottomNavigationMenuView.getChildAt(position);
        int itemWidth = v.getWidth();
        int left = (screenSize[0] - itemWidth * getTabSize()) / 2 + position * itemWidth;
        layoutParams.leftMargin = left + getTabMsgHitLeft(itemWidth);
        layoutParams.topMargin = getTabMsgHitTop();
        return layoutParams;
    }

    protected int getTabMsgHitLeft(int itemWidth) {
        int itemIconSize = mBottomNavigationView.getItemIconSize();
        return (itemIconSize + itemWidth) / 2 - AndroidTools.dip2px(this, 8);
    }

    protected int getTabMsgHitTop() {
        return AndroidTools.dip2px(this, 5);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mViewPager.setCurrentItem(getPosition(menuItem), mViewPager.isScrollable());
        // 返回false menu点击没响应
        return true;
    }

    private int getPosition(MenuItem menuItem) {
        Menu menu = mBottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item == menuItem) {
                return i;
            }
        }
        return 0;
    }

    private int getItemMenuId(int position) {
        MenuItem item = mBottomNavigationView.getMenu().getItem(position);
        return item.getItemId();
    }

    @Override
    public void onBackPressed() {
        ExitTools.getInstance().exit(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mBottomNavigationView.setSelectedItemId(getItemMenuId(i));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
