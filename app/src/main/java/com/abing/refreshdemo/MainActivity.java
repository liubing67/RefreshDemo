package com.abing.refreshdemo;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.abing.refreshdemo.base.BaseActivity;
import com.abing.refreshdemo.ui.fragment.RefreshRecyclerViewFragment;
import com.abing.refreshdemo.ui.fragment.RefreshSwipeListViewFragment;
import com.abing.refreshdemo.ui.fragment.RefreshSwipeRecyclerViewFragment;

import cn.bingoogolapple.bgabanner.BGAViewPager;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int LOADING_DURATION = 2000;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private BGAViewPager mViewPager;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mDrawerLayout = getViewById(R.id.drawerLayout);
        mNavigationView = getViewById(R.id.navigationView);
        mToolbar = getViewById(R.id.toolbar);
        mViewPager = getViewById(R.id.viewPager);
    }

    @Override
    protected void setListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                hideDrawer();
                if (menuItem.getItemId() == R.id.navigation_main_stickyNav) {
//                    startActivity(new Intent(MainActivity.this, StickyNavActivity.class));
                } else {
                    setTitle(menuItem.getTitle());
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_main_gridview:
                            mViewPager.setCurrentItem(0, false);
                            break;
                        case R.id.navigation_main_normallistview:
                            mViewPager.setCurrentItem(1, false);
                            break;
                        case R.id.navigation_main_normalrecyclerview:
                            mViewPager.setCurrentItem(2, false);
                            break;
                        case R.id.navigation_main_swipelistview:
                            mViewPager.setCurrentItem(3, false);
                            break;
                        case R.id.navigation_main_swiperecyclerview:
                            mViewPager.setCurrentItem(4, false);
                            break;
                        case R.id.navigation_main_staggeredgridlayoutmanager:
                            mViewPager.setCurrentItem(5, false);
                            break;
                        case R.id.navigation_main_scrollview:
                            mViewPager.setCurrentItem(6, false);
                            break;
                        case R.id.navigation_main_normalview:
                            mViewPager.setCurrentItem(7, false);
                            break;
                        case R.id.navigation_main_webview:
                            mViewPager.setCurrentItem(8, false);
                            break;
                        default:
                            break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        setTitle(R.string.gridview_demo);

        setUpNavDrawer();
        setUpViewPager();
    }

    private void setUpNavDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setUpViewPager() {
        mViewPager.setAllowUserScrollable(false);
        mViewPager.setAdapter(new ContentViewPagerAdapter(getSupportFragmentManager(), this));
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            hideDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private static class ContentViewPagerAdapter extends FragmentPagerAdapter {
//        private Class[] mFragments = new Class[]{RefreshGridViewFragment.class, RefreshListViewFragment.class, RefreshRecyclerViewFragment.class, RefreshSwipeListViewFragment.class, RefreshSwipeRecyclerViewFragment.class, RefreshStaggeredRecyclerViewFragment.class, RefreshScrollViewFragment.class, RefreshNormalViewFragment.class, RefreshWebViewFragment.class};
        private Class[] mFragments = new Class[]{RefreshSwipeListViewFragment.class, RefreshSwipeListViewFragment.class, RefreshRecyclerViewFragment.class, RefreshSwipeListViewFragment.class, RefreshSwipeRecyclerViewFragment.class, RefreshSwipeListViewFragment.class, RefreshSwipeListViewFragment.class, RefreshSwipeListViewFragment.class, RefreshSwipeListViewFragment.class};
        private Context mContext;

        public ContentViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(mContext, mFragments[position].getName());
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }
}
