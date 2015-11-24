package com.android.jamalludin.pamfunnext;

import android.app.FragmentManager;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RelativeLayout drawerPen;
    ListView nav;

    List<NavItem> navItemList;
    List<Fragment> fragmentList;

    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPen = (RelativeLayout) findViewById(R.id.drawer_pane);
        nav = (ListView) findViewById(R.id.nav_list);

        navItemList = new ArrayList<NavItem>();

        navItemList.add(new NavItem("Home", "Home Page", R.drawable.home));
        navItemList.add(new NavItem("Setting", "Setting Page", R.drawable.setting));
        navItemList.add(new NavItem("About", "About Page", R.drawable.about));

        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext(), R.layout.item_nav_list, navItemList);

        nav.setAdapter(navListAdapter);

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new Home());
        fragmentList.add(new Setting());
        fragmentList.add(new About());

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, fragmentList.get(0)).commit();

        setTitle(navItemList.get(0).getTitle());
        nav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerPen);

        nav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, fragmentList.get(position)).commit();

                setTitle(navItemList.get(position).getTitle());
                nav.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerPen);


            }

        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }
}
