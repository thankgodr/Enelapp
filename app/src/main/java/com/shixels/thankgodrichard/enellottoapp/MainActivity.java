package com.shixels.thankgodrichard.enellottoapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixels.thankgodrichard.enellottoapp.Games.Gametype;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    RelativeLayout titleContainerUp;
    FancyButton menuoprn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleContainerUp = (RelativeLayout) findViewById(R.id.titlecontainer);
        menuoprn = (FancyButton) findViewById(R.id.menuopen);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       //drawer.setDrawerListener(toggle);
       // toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new Gametype();
        openFragment2(fragment,R.id.mainContainer);
        menuoprn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openFragment(Fragment frag, int containerId, String stack){
        ViewGroup  display = (ViewGroup) findViewById(containerId);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(stack);
        display.removeAllViews();
        ft.replace(containerId,frag);
        ft.commit();
    }
    public void configTitleUp(@Nullable ImageView imageTitle, @Nullable TextView textTitle){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(120, 62);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        if(imageTitle == null){
            textTitle.setLayoutParams(layoutParams);
            if(titleContainerUp.getChildAt(2) != null){
                for(int i = 2; i < titleContainerUp.getChildCount(); i++){
                        titleContainerUp.getChildAt(i).setVisibility(View.GONE);
                }
            }
            titleContainerUp.addView(textTitle);
        }
        else {

            imageTitle.setLayoutParams(layoutParams);
            if(titleContainerUp.getChildAt(2) != null){
                for(int i = 2; i < titleContainerUp.getChildCount(); i++){
                    titleContainerUp.getChildAt(i).setVisibility(View.GONE);
                }
            }
            titleContainerUp.addView(imageTitle);
        }
    }
    public void resetMenu(){
        menuoprn.setIconResource("\uf0c9");
        menuoprn.setOnClickListener(null);
        menuoprn.setFontIconSize(20);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuoprn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

    }
    public void changeBack(final Fragment frag){
        menuoprn.setIconResource("\uf053");
        menuoprn.setOnClickListener(null);
        menuoprn.setFontIconSize(20);
        menuoprn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment2(frag,R.id.mainContainer);
            }
        });
    }
    public void openFragment2(Fragment frag, int containerId){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerId,frag);
        ft.commit();
    }
}
