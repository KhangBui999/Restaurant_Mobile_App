/*
 * Created by Khang Bui (z5209606) on 24/03/20 8:15 PM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 24/03/20 8:12 PM.
 */

package com.example.sydneyrestaurantlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RestaurantAdapter.RecyclerViewClickListener listener;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mStatus;
    private ArrayList<Restaurant> list;
    private ArrayList<String> nameList;
    private boolean wideMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Changes title of the action bar
        getSupportActionBar().setTitle("Sydney Restaurants Guide");

        //Checks if device is in wideMode by seeing if the scrollContainer exists
        if (findViewById(R.id.scrollContainer) == null) {
            wideMode = false;
        } else {
            wideMode = true;
        }

        //Sets the status message of how many items were loaded and how it was sorted
        mStatus = findViewById(R.id.subheading);
        mStatus.setText("Showing 10 (of 10) - Sorted By Highest Rating");

        //Retrieves initial ArrayList which is ordered from highest rating
        list = DataUtility.quickSortDescRating(DataUtility.getDefaultList());
        nameList = new ArrayList<>();
        for (Restaurant r : list) {
            nameList.add(r.getName()); //nameList is required to determine how ArrayList is ordered
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
                if (wideMode) {
                    //Tablet (wide mode) has a dual pane view via a fragment in the scrollContainer
                    FragmentManager myManager = getSupportFragmentManager();
                    FragmentTransaction myTransaction = myManager.beginTransaction();
                    Fragment myFragment = new DetailFragment();
                    Bundle arguments = new Bundle();
                    arguments.putInt("POSITION", position);
                    arguments.putStringArrayList("NAMES", nameList);
                    myFragment.setArguments(arguments);
                    myTransaction.replace(R.id.scrollContainer, myFragment);
                    myTransaction.commit();
                } else {
                    //Handheld launches another activity
                    launchDetailActivity(position);
                }
            }
        };

        //Displays a the full restaurant list with the above RecyclerViewClickListener
        mAdapter = new RestaurantAdapter(list, listener);
        mRecyclerView.setAdapter(mAdapter);

    }

    //Launches detail activity
    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("POSITION", position); //needed for position of ArrayList
        intent.putExtra("NAMES", nameList); //needed for how ArrayList is ordered
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

    /**
     * launchFilterDialog launches a dialog that allows users to adjust sorting options
     */
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

    //Todo: for portfolio purposes, clean up refreshList method and improve readability

    /**
     * Refreshes adapter ArrayList based on sorting option
     *
     * @param rbId is the id of the radio button checked
     */
    private void refreshList(int rbId) {
        switch (rbId) {
            case R.id.rb_hr:
                list = new ArrayList<>(); //Clears array list
                list = DataUtility.quickSortDescRating(DataUtility.getDefaultList());
                nameList = new ArrayList<>(); //Clears nameList
                for (Restaurant r : list) {
                    nameList.add(r.getName()); //matches nameList to new ArrayList
                }
                mAdapter = new RestaurantAdapter(list, listener); //resets Adapter
                mRecyclerView.setAdapter(mAdapter); //resets Adapter
                mRecyclerView.startLayoutAnimation(); //starts Animation
                mStatus.setText("Showing 10 (of 10) - Sorted By Highest Rating");
                break;
            case R.id.rb_lr:
                list = new ArrayList<>();
                list = DataUtility.quickSortAscRating(DataUtility.getDefaultList());
                nameList = new ArrayList<>();
                for (Restaurant r : list) {
                    nameList.add(r.getName());
                }
                mAdapter = new RestaurantAdapter(list, listener);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.startLayoutAnimation();
                mStatus.setText("Showing 10 (of 10) - Sorted By Lowest Rating");
                break;
            case R.id.rb_az:
                list = new ArrayList<>();
                list = DataUtility.quickSortAlpha(DataUtility.getDefaultList());
                nameList = new ArrayList<>();
                for (Restaurant r : list) {
                    nameList.add(r.getName());
                }
                mAdapter = new RestaurantAdapter(list, listener);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.startLayoutAnimation();
                mStatus.setText("Showing 10 (of 10) - Sorted Alphabetically (A-Z)");
                break;
            case R.id.rb_za:
                list = new ArrayList<>();
                list = DataUtility.quickSortReverseAlpha(DataUtility.getDefaultList());
                nameList = new ArrayList<>();
                for (Restaurant r : list) {
                    nameList.add(r.getName());
                }
                mAdapter = new RestaurantAdapter(list, listener);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.startLayoutAnimation();
                mStatus.setText("Showing 10 (of 10) - Sorted Alphabetically (Z-A)");
                break;
            default:
                break;
        }
    }
}
