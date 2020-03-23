/*
 * Created by Khang Bui (z5209606) on 24/03/20 12:11 AM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 23/03/20 11:34 PM.
 */

package com.example.sydneyrestaurantlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class DetailFragment extends Fragment {

    private ImageView mImage;
    private TextView mName;
    private RatingBar mRatingBar;
    private TextView mRating;
    private TextView mCuisine;
    private TextView mLocation;
    private TextView mDesc;
    private TextView mAddress;
    private TextView mPhone;
    private TextView mWebsite;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        //Retrieves arguments from the FragmentManager in DetailActivity
        Bundle arguments = getArguments();
        String name = arguments.getString("NAME");

        DataUtility du = new DataUtility(); //Creates a DataUtility class to use utility methods
        Restaurant restaurant = du.searchRestaurant(name); //searches for restaurant based on name

        //Links XML elements to their respective control variable
        mImage = v.findViewById(R.id.imageView);
        mName = v.findViewById(R.id.tv_name);
        mRatingBar = v.findViewById(R.id.ratingBar);
        mRating = v.findViewById(R.id.tv_rating);
        mCuisine = v.findViewById(R.id.tv_cuisine);
        mLocation = v.findViewById(R.id.tv_location);
        mDesc = v.findViewById(R.id.tv_desc);
        mAddress = v.findViewById(R.id.tv_address);
        mPhone = v.findViewById(R.id.tv_phone);
        mWebsite = v.findViewById(R.id.tv_website);

        //Sets the values of the XML element
        mImage.setImageResource(restaurant.getIvId()); //sets image resource based on Id number
        mName.setText(restaurant.getName());
        mRatingBar.setRating(restaurant.getRating()); //sets rating value
        mRatingBar.setStepSize(0.1f); //sets how filled the bar is
        mRating.setText(String.format("%,.1f", restaurant.getRating()));
        mCuisine.setText(restaurant.listCuisine());
        mLocation.setText(restaurant.getSuburb());
        mDesc.setText(restaurant.getDesc());
        mAddress.setText(restaurant.getAddress());
        mPhone.setText(restaurant.getPhone());
        mWebsite.setText(restaurant.getFormattedLink());

        return v;
    }
}
