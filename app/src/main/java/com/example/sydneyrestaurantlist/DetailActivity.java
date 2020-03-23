/*
 * Created by Khang Bui (z5209606) on 24/03/20 12:11 AM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 24/03/20 12:10 AM.
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
        String name = intent.getStringExtra("NAME");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Manages the fragment for DetailActivity
        FragmentManager myManager = getSupportFragmentManager();
        FragmentTransaction myTransaction = myManager.beginTransaction();
        Fragment myFragment = new DetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString("NAME", name);
        myFragment.setArguments(arguments);
        myTransaction.replace(R.id.scrollView, myFragment);
        myTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
