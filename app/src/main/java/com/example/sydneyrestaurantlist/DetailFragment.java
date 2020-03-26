/*
 * Created by Khang Bui (z5209606) on 24/03/20 8:15 PM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 24/03/20 6:44 PM.
 */

package com.example.sydneyrestaurantlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        //Retrieves arguments from the FragmentManager in DetailActivity
        Bundle arguments = getArguments();
        int position = arguments.getInt("POSITION");
        String transitionName = arguments.getString("TRANSITION");

        final Restaurant restaurant = DataUtility.getRestaurantList().get(position);

        //Links XML elements to their respective control variable
        ImageView mImage = v.findViewById(R.id.imageView);
        TextView mName = v.findViewById(R.id.tv_name);
        RatingBar mRatingBar = v.findViewById(R.id.ratingBar);
        TextView mRating = v.findViewById(R.id.tv_rating);
        TextView mCuisine = v.findViewById(R.id.tv_cuisine);
        TextView mLocation = v.findViewById(R.id.tv_location);
        TextView mDesc = v.findViewById(R.id.tv_desc);
        TextView mAddress = v.findViewById(R.id.tv_address);
        TextView mPhone = v.findViewById(R.id.tv_phone);
        TextView mWebsite = v.findViewById(R.id.tv_website);

        //Bitmap enables the efficient loading of images into the ImageView
        //Used as a fix for devices running on older hardware
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap source = BitmapFactory.decodeResource(mImage.getResources(),
                restaurant.getIvId(), options); //gets a scaled down Bitmap

        //Sets the values of the XML element
        mImage.setImageBitmap(source); //sets image resource based on Id number
        mName.setText(restaurant.getName());
        mRatingBar.setRating(restaurant.getRating()); //sets rating value
        mRatingBar.setStepSize(0.1f); //sets how filled the bar is
        mRating.setText(String.format("%,.1f", restaurant.getRating()));
        mCuisine.setText(restaurant.listCuisine());
        mLocation.setText(restaurant.getSuburb());
        mDesc.setText(restaurant.getDesc());
        mAddress.setText(restaurant.getFormattedAddress());
        mPhone.setText(restaurant.getPhone());
        mWebsite.setText(restaurant.getFormattedLink());

        //sets an onClick for the map app launch with marker pointing to location
        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMap(restaurant.getAddress());
            }
        });

        //sets an onClick for the phone text to have a the number entered in the Phone app
        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPhoneDial(restaurant.getDial());
            }
        });

        //sets an onClick for the website text to open the specified website link
        mWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWebsite(restaurant.getWebsite());
            }
        });

        return v;
    }

    //Launches Map
    private void launchMap(String address) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+address));
        startActivity(intent);
    }

    //Enters number into dial
    private void launchPhoneDial(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
        startActivity(intent);
    }

    //Launches website link
    private void launchWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
