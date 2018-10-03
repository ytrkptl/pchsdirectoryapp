package com.example.android.pchsdirectoryapp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //set the toolbar to use the custom toolbar from activity_main.xml
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_white_24);

        // Find the view pager that will allow the user to swipe between fragments
        final ViewPager viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        viewPager.setAdapter(new CategoryAdapter(getSupportFragmentManager(),
                MainActivity.this));
        // Set the adapter onto the view pager
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        drawerLayout = findViewById(R.id.drawerLayout);

        // https://abhiandroid.com/materialdesign/navigation-drawer
        // http://www.devexchanges.info/2016/05/android-basic-training-course-combining.html
        // https://trinitytuts.com/navigation-drawer-with-tabview-in-android/
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();//get selected menu item's id
                //check which menu item is selected and perform steps accordingly
                if (itemId == R.id.hotels_drawer_id){
                    viewPager.setCurrentItem(0);//set the viewPager to open the first tab
                    drawerLayout.closeDrawers();//then close the drawer
                } else if (itemId == R.id.restaurants_drawer_id){
                    viewPager.setCurrentItem(1);//set the viewPager to open the second tab
                    drawerLayout.closeDrawers();//then close the drawer
                } else if (itemId == R.id.schools_drawer_id){
                    viewPager.setCurrentItem(2);//set the viewPager to open the third tab
                    drawerLayout.closeDrawers();//then close the drawer
                } else if (itemId == R.id.shopping_drawer_id){
                    viewPager.setCurrentItem(3);//set the viewPager to open the fourth tab
                    drawerLayout.closeDrawers();//then close the drawer
                }   else if (itemId == R.id.favorites_drawer_id){
                    viewPager.setCurrentItem(4);//set the viewPager to open the fourth tab
                    drawerLayout.closeDrawers();//then close the drawer
                }
                return true;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // the following line will check if drawer is open or not
                boolean isOpen = drawerLayout.isDrawerOpen(GravityCompat.END);
                //set an if else condition to close drawer if open on click of menu button
                //else open drawer
                if (isOpen) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}