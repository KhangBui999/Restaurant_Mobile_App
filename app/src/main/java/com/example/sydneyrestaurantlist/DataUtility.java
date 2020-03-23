/*
 * Created by Khang Bui (z5209606) on 24/03/20 12:11 AM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 23/03/20 11:46 PM.
 */

package com.example.sydneyrestaurantlist;

import java.util.ArrayList;

//Utility class to manage restaurant data
public class DataUtility {

    //Empty constructor class because needed methods aren't static
    public DataUtility() {
    }

    public ArrayList<Restaurant> getRestaurantList() {
        ArrayList<Restaurant> restaurants = new ArrayList();
        restaurants.add(getHolyHeffas());
        return restaurants;
    }

    //The following methods return a certain restaurant
    public Restaurant getHolyHeffas() {
        String name = "Holy Heffas";
        int ivId = R.drawable.holyheffa;
        float rating = 4.8f;
        ArrayList<String> cuisine = new ArrayList();
        cuisine.add("$$");
        cuisine.add("American");
        cuisine.add("Burgers");
        String suburb = "Edensor Park, NSW 2176";
        String desc = "Welcome to Holy Heffa - a burger truck devoted to serving affordable, delicious burgers.\n\n" +
                "Our menu changes regularly, with a different milkshake, special and topping for fries" +
                " each week. But you'll be coming here to eat our meat: freshly ground angus formed " +
                "into patties, grilled to medium-rare, and sandwiched within brioche-like buns. With " +
                "little exception, our burgers are delicious, juicy and messy, enhanced by a carefully " +
                "selected toppings.";
        String address = "661-671 Smithfield Rd, Edensor Park NSW 2176";
        String phone = "0414 046 726";
        String website = "http://www.holyheffa.com.au/menu";
        return new Restaurant(name, ivId, rating, cuisine, suburb, desc, address, phone, website);
    }

    //Searches ArrayList and returns a restaurant based on name result
    public Restaurant searchRestaurant(String name) {
        ArrayList<Restaurant> restaurants = getRestaurantList();
        Restaurant result = null;
        for (Restaurant restaurant : restaurants) {
            if(restaurant.getName().equals(name)){
                result = restaurant;
                break;
            }
        }
        return result;
    }

}
