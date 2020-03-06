package com.example.mobileprogfinalproject;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Toast;

import java.util.List;

public class HomePage extends AppCompatActivity implements AppBarConfiguration.OnNavigateUpListener {

    private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton addPassword;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private NavController navController;
    private ActionBarDrawerToggle drawerToggle;
    private Intent intent;
    private int accountID;
    private Bundle extras;
    private AlertDialog.Builder confirmLogoutBuilder;
    private AlertDialog confirmLogout;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_drawer);


        initializeWidgets();
        setSupportActionBar(toolbar);




        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        drawer.addDrawerListener(drawerToggle);



        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_settings, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();


        navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                logout();
                return true;
            }
        });

        addPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccountID();
                intent = new Intent(getApplicationContext(),AddPasswordPage.class);
                intent.putExtra("ACCOUNT_ID",accountID);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){

            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }




    @Override
    public boolean onSupportNavigateUp() {

        navController = Navigation.findNavController(this, R.id.fragment_container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void logout(){
        confirmLogoutBuilder = new AlertDialog.Builder(this);
        confirmLogoutBuilder.setMessage("Are you sure you want to logout?");
        confirmLogoutBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        confirmLogoutBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        confirmLogout = confirmLogoutBuilder.create();
        confirmLogout.show();
    }



    public FloatingActionButton getFloatingActionButton() {
        return addPassword;
    }

    public int retrieveAccountID(){
        getAccountID();
        return accountID;
    }

    private void getAccountID(){
        extras = getIntent().getExtras();
        if(extras != null){
            accountID = extras.getInt("ACCOUNT_ID");
        }
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
    }


    private void initializeWidgets(){
        addPassword = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }






}
