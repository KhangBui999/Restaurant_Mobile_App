/*
 * Created by Khang Bui (z5209606) on 24/03/20 6:33 PM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 24/03/20 3:15 AM.
 */

package com.example.sydneyrestaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", 0);

        getSupportActionBar().setTitle(DataUtility.getRestaurantList().get(position).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //includes back button

        //Manages the fragment for DetailActivity
        FragmentManager myManager = getSupportFragmentManager();
        FragmentTransaction myTransaction = myManager.beginTransaction();
        Fragment myFragment = new DetailFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("POSITION", position);
        myFragment.setArguments(arguments);
        myTransaction.replace(R.id.scrollView, myFragment);
        myTransaction.commit();
    }

    //Allows the back button to navigate to the previous Activity
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
