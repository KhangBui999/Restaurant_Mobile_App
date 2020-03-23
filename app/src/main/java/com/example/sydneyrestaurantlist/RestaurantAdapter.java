/*
 * Created by Khang Bui (z5209606) on 24/03/20 2:29 AM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 24/03/20 2:29 AM.
 */

package com.example.sydneyrestaurantlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestViewHolder> {
    private ArrayList<Restaurant> mRestaurants;
    private RecyclerViewClickListener mListener;

    //RestaurantAdapter Constructor
    public RestaurantAdapter(ArrayList<Restaurant> restaurants, RecyclerViewClickListener listener) {
        mRestaurants = restaurants;
        mListener = listener;
    }

    //RecyclerViewClick Listener interface
    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    //Static RestViewHolder class
    public static class RestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImage;
        public TextView mName, mCuisine, mLocation, mRating;
        public RatingBar mRateBar;
        public RecyclerViewClickListener mListener;

        public RestViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            mImage = v.findViewById(R.id.imageView2);
            mName = v.findViewById(R.id.tv_name2);
            mCuisine = v.findViewById(R.id.tv_cuisine2);
            mLocation = v.findViewById(R.id.tv_location2);
            mRating = v.findViewById(R.id.tv_rating2);
            mRateBar = v.findViewById(R.id.ratingBar2);
        }

        //onClick method from RecyclerViewClickListener interface
        @Override
        public void onClick(View view) { mListener.onClick(view, getAdapterPosition()); }
    }

    //Creates RestViewHolder (layout object) and sets it
    @Override
    public RestaurantAdapter.RestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_list_row, parent, false);
        return new RestViewHolder(v, mListener);
    }

    //Sets values for XML elements
    @Override
    public void onBindViewHolder(RestViewHolder holder, int position) {
        Restaurant restaurant = mRestaurants.get(position);
        holder.mImage.setImageResource(restaurant.getIvId());
        holder.mName.setText(restaurant.getName());
        holder.mCuisine.setText(restaurant.listCuisine());
        holder.mLocation.setText(restaurant.getSuburb());
        holder.mRating.setText(String.format("%,.1f", restaurant.getRating()));
        holder.mRateBar.setRating(restaurant.getRating()); //sets rating value
        holder.mRateBar.setStepSize(0.1f); //sets how filled the bar is
    }

    //Needed for RecyclerView
    @Override
    public int getItemCount() { return mRestaurants.size(); }

}
