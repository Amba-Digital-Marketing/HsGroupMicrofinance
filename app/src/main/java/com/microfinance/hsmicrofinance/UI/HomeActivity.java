package com.microfinance.hsmicrofinance.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.ActivityHomeBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import timber.log.Timber;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";
    public static ActivityHomeBinding mActivityHomeBinding;
    private NavController mNavController;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;
    private BottomNavigationView mNavView;
    private AppBarConfiguration mAppBarConfiguration;
    View mView;
    private TextView mEmail;
    private TextView mName;
    private HomeActivityViewModel model;
    double balance;
    private HomeActivityViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mActivityHomeBinding.getRoot());

        mDrawerLayout = mActivityHomeBinding.drawerLayout;
        mNavigationView = mActivityHomeBinding.navigationView;
        mViewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        try {

            mNavigationView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(v -> {

                onUserLogout(1);
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mView = mNavigationView.getHeaderView(0);
            mEmail = mView.findViewById(R.id.etEmail);
            mName = mView.findViewById(R.id.etName);


            model = new ViewModelProvider(this).get(HomeActivityViewModel.class);
            model.getUserData();
            model.dashBoardDataMutableLiveData.observe(this, dashboardData -> {
                mEmail.setText(dashboardData.getEmail());
                mName.setText(dashboardData.getName());
                this.balance = Double.parseDouble(dashboardData.getAccBallance());
                getToken(dashboardData.getEmail());

                //bottom nav actions
                setBottomNavActions(balance);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        mToolbar = mActivityHomeBinding.toolbar;
        mToolbar.setTitle("Micro finance");
        setSupportActionBar(mToolbar);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavController = Navigation.findNavController(HomeActivity.this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(mNavigationView, mNavController);

        //method fetches from view model
        getBanksAndCurrencies();


    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setBottomNavActions(double balance) {
        mNavView = findViewById(R.id.bottomNavigationView);

        if (balance < 500) {
            mNavView.getMenu().getItem(1).setEnabled(false);
            mNavView.getMenu().getItem(2).setEnabled(false);
            mNavView.getMenu().getItem(3).setEnabled(false);


            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.basicDashboard, R.id.basicAccount)
                    .build();

        } else {

            //Pass the ID's of Different destinations
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.basicDashboard, R.id.basicLoanPackages, R.id.basicInvestmentPackages, R.id.basicAccount)
                    .build();

        }


        //Initialize NavController.
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mNavView, navController);


    }


    //getting currencies banks
    private void getBanksAndCurrencies() {
        model.getBankDetails(this);
        model.getCurrenciesDetails(this);
        model.getCountriesDetails(this);
    }


    public void getToken(String email) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(
                task -> {
                    if (!task.isSuccessful()) {
                        Log.e(TAG, "onComplete: failed to get token");
                    }

                    String token = task.getResult();
                    Log.d(TAG, "onComplete: Token is " + token);
                    try {
                        MyTimber.setFirebaseToken(token);
                        mViewModel.sendTokenToServer(HomeActivity.this,email,token);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        ).addOnFailureListener(e -> Log.e(TAG, "onComplete: failed to get token" + e.getLocalizedMessage()));

    }

    private void onUserLogout(int uid) {
        UserDao db = UserDB.getDbInstance(this).userDao();
        UserEntity entity = db.loaduserById(1);
        db.delete(entity);
        startActivity(new Intent(HomeActivity.this, BaseLogin.class));

        this.finishAffinity();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: new intent called");

    }


}