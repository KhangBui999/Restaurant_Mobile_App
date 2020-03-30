/*
 * Created by Khang Bui (z5209606) on 24/03/20 8:15 PM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 24/03/20 8:12 PM.
 */

package com.example.sydneyrestaurantlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mStatus;
    private ArrayList<Restaurant> list;
    private ArrayList<String> nameList;
    RestaurantAdapter.RecyclerViewClickListener listener;
    private boolean wideMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Changes title of the action bar
        getSupportActionBar().setTitle("Sydney Restaurants Guide");

        mStatus = findViewById(R.id.subheading);
        mStatus.setText("Showing 10 (of 10) - Sorted By Highest Rating");

        //Retrieves initial ArrayList (no filters)
        list = DataUtility.quickSortDescRating(DataUtility.getDefaultList());
        nameList = new ArrayList<>();
        for(Restaurant r : list) {
            nameList.add(r.getName());
        }

        //Manages layout of rvList
        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Creates a new RecyclerViewClickListener
        listener = new RestaurantAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                launchDetailActivity(position);
            }
        };

        //Displays a the full restaurant list with the above RecyclerViewClickListener
        mAdapter = new RestaurantAdapter(list, listener);
        mRecyclerView.setAdapter(mAdapter);

    }

    //Launches detail activity
    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("POSITION", position);
        intent.putExtra("NAMES", nameList);
        startActivity(intent);
        this.overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in); //transition
    }

    //Used to have custom menu (with custom options button) in the ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.testmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Needed to handle when button is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.filterbutton) {
            launchFilterDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_filter, null);

        builder.setView(view);
        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                RadioGroup rg = view.findViewById(R.id.radioGroup);
                refreshList(rg.getCheckedRadioButtonId());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // No action occurs when user clicks "Cancel"
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Todo: clean up refreshList method and improve readability
    //Refreshes List when options are applied
    private void refreshList(int rbId) {
        switch(rbId){
            case R.id.rb_hr:
                list = new ArrayList<>();
                list = DataUtility.quickSortDescRating(DataUtility.getDefaultList());
                nameList = new ArrayList<>();
                for(Restaurant r : list) {
                    nameList.add(r.getName());
                }
                mAdapter = new RestaurantAdapter(list, listener);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.startLayoutAnimation();
                mStatus.setText("Showing 10 (of 10) - Sorted By Highest Rating");
                break;
            case R.id.rb_lr:
                list = new ArrayList<>();
                list = DataUtility.quickSortAscRating(DataUtility.getDefaultList());
                nameList = new ArrayList<>();
                for(Restaurant r : list) {
                    nameList.add(r.getName());
                }
                mAdapter = new RestaurantAdapter(list, listener);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.startLayoutAnimation();
                mStatus.setText("Showing 10 (of 10) - Sorted By Lowest Rating");
                break;
            default:
                break;
        }
    }
}
