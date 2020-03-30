/*
 * Created by Khang Bui (z5209606) on 24/03/20 8:15 PM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 24/03/20 6:33 PM.
 */

package com.example.sydneyrestaurantlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", 0);
        ArrayList<String> nameList = intent.getStringArrayListExtra("NAMES");

        //Makes ActionBar transparent
        Objects.requireNonNull(getSupportActionBar()).setTitle(null); //sets title as empty
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //includes back button

        //Manages the fragment for DetailActivity
        FragmentManager myManager = getSupportFragmentManager();
        FragmentTransaction myTransaction = myManager.beginTransaction();
        Fragment myFragment = new DetailFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("POSITION", position);
        arguments.putStringArrayList("NAMES", nameList);
        myFragment.setArguments(arguments);
        myTransaction.replace(R.id.scrollView, myFragment);
        myTransaction.commit();
    }

    //Allows the back button to navigate to MainActivity
    @Override
    public boolean onSupportNavigateUp(){
        finish(); //finishes Activity and returns to previous Activity i.e. MainActivity
        this.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out); //transition
        return true;
    }

}
