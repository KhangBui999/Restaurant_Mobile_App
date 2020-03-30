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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;


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
        ArrayList<String> nameList = arguments.getStringArrayList("NAMES");

        final Restaurant restaurant = DataUtility.searchRestaurant(nameList.get(position));

        //Links XML elements to their respective control variable
        ImageView mImage = v.findViewById(R.id.imageView);
        TextView mName = v.findViewById(R.id.tv_name);
        RatingBar mRatingBar = v.findViewById(R.id.ratingBar);
        TextView mRating = v.findViewById(R.id.tv_rating);
        ChipGroup mCuisine = v.findViewById(R.id.cg_cuisine);
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
        mRating.setText(String.format("%,.1f", restaurant.getRating()));
        mLocation.setText(restaurant.getSuburb());
        mDesc.setText(restaurant.getDesc());
        mAddress.setText(restaurant.getFormattedAddress());
        mPhone.setText(restaurant.getPhone());
        mWebsite.setText(restaurant.getFormattedLink());

        //Adds chips based on the cuisine ArrayList
        mCuisine.removeAllViews(); //added to ensure duplication bug
        ArrayList<String> cuisineList = restaurant.getCuisine();
        for (String cuisine : cuisineList) {
            Chip chip = new Chip(getActivity(), null, R.attr.CustomChipChoiceStyle);
            chip.setText(cuisine);
            mCuisine.addView(chip);
        }

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

    //Launches Map using the geolocation query
    private void launchMap(String address) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
        startActivity(intent);
    }

    //Enters number into dial e.g. tel:000
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
