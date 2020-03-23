/*
 * Created by Khang Bui (z5209606) on 24/03/20 12:11 AM.
 * This is an academic project completed as part of the UNSW course, INFS3634.
 * Copyright (c) 2020. All rights reserved.
 * Last modified 23/03/20 11:03 PM.
 */

package com.example.sydneyrestaurantlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDetailActivity();
            }
        });
    }

    private void launchDetailActivity() {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("NAME", "Holy Heffas");
        startActivity(intent);
    }
}
