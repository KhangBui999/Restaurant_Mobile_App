/*
 * Created by Khang Bui (z5209606) on 23/03/20 9:30 PM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 23/03/20 9:25 PM.
 */

package com.example.sydneyrestaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Manages the fragment for DetailActivity
        FragmentManager myManager = getSupportFragmentManager();
        FragmentTransaction myTransaction = myManager.beginTransaction();
        Fragment myFragment = new DetailFragment();
        Bundle arguments = new Bundle();
        myFragment.setArguments(arguments);
        myTransaction.replace(R.id.scrollView, myFragment);
        myTransaction.commit();
    }
}
