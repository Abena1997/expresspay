package com.expresspay.access_control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.expresspay.access_control.models.GuestCheckedInData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Fragment fragmentCheckInPopulated =new CheckInPopulatedStateFragment();
//        FragmentTransaction transactionNoGuest = getSupportFragmentManager().beginTransaction();
//
//        transactionNoGuest.add(R.id.fragment_container_fl, fragmentNoGuest);
//        transactionNoGuest.addToBackStack(null);
//
//        transactionNoGuest.commit();
    }


    @Override
    public void onBackPressed() {

        // get the current fragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_fl);
        if (currentFragment instanceof NoCheckedInGuestFragment ||currentFragment instanceof CheckInPopulatedStateFragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(areGuestsCheckedIn()){
            Fragment fragmentGuest = new CheckInPopulatedStateFragment();
            loadFragment(fragmentGuest);
        }
        else {
            Fragment fragmentNoGuest  = new NoCheckedInGuestFragment();
            loadFragment(fragmentNoGuest);
        }
    }

    void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_fl, fragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    boolean areGuestsCheckedIn(){
        // check if guest data exists in our database
        // if yes, return true
        // if no, return false

        Realm realm = Realm.getDefaultInstance();
        final RealmResults<GuestCheckedInData> guestCheckedInData = realm.where(GuestCheckedInData.class).findAll();
        Log.e("Data", "onResume: " + guestCheckedInData.size());

        if (guestCheckedInData.size() == 0){
            return false;
        } else {
            return true;
        }
    }
}
