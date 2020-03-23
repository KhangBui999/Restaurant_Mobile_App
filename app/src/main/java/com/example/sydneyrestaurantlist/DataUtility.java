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

    public static ArrayList<Restaurant> getRestaurantList() {
        ArrayList<Restaurant> restaurants = new ArrayList();
        restaurants.add(getHolyHeffas());
        restaurants.add(getMarysNewtown());
        return restaurants;
    }

    //TO-DO: Add 8 more restaurants
    //--> Middle-Eastern: Watsup Brothers, El-Jannahs - Punchbowl
    //--> Thai: It's Time for Thai
    //--> Japanese: Manpuku Ramen - Kingsford, Sakura Fresh Sushi
    //--> Korean: Red Pepper Bistro, Jang Ta Bal
    //--> Vietnamese: Marrickville Pork Roll

    //The following methods return a certain restaurant
    public static Restaurant getHolyHeffas() {
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

    public static Restaurant getMarysNewtown() {
        String name = "Mary's Newtown";
        int ivId = R.drawable.marysnewtown;
        float rating = 4.4f;
        ArrayList<String> cuisine = new ArrayList();
        cuisine.add("$$");
        cuisine.add("American");
        cuisine.add("Burgers");
        cuisine.add("Bar (18+)");
        String suburb = "Newtown, NSW 2042";
        String desc = "Craft beer and burgers in a rustic bar with timber decor, a mezzanine and a " +
                "rock 'n' roll vibe.\n\nNOTE: To enter this premises you need to be 18+ or be " +
                "accompanied by a legal guardian.";
        String address = "6 Mary St, Newtown NSW 2042";
        String phone = "(02) 9002 0683";
        String website = "https://www.marys69.com/#/newtown/";
        return new Restaurant(name, ivId, rating, cuisine, suburb, desc, address, phone, website);
    }

    //Searches ArrayList and returns a restaurant based on name result
    public static Restaurant searchRestaurant(String name) {
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
