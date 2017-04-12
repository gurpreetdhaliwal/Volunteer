package com.example.admin.volunteer;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;


/**
 * Created by Admin on 30-03-2017.
 */

public class volunteerhome extends AppCompatActivity{
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteerhome);
        fm = getSupportFragmentManager();


        FragmentTransaction ft = fm.beginTransaction();
       Home cm = new Home();
        ft.replace(R.id.frame_id, cm);
        ft.commit();

    }

    public void openhome(View v){
        FragmentTransaction ft =fm.beginTransaction();
       Home cm = new Home();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }
    public void openngo(View v){
        FragmentTransaction ft =fm.beginTransaction();
        ngo cm = new ngo();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void openevents(View v){
        FragmentTransaction ft =fm.beginTransaction();
        events cm = new events();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void openaccount(View v){
        FragmentTransaction ft =fm.beginTransaction();
        account cm = new account();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void open_volunteer_profile(View v)
    {
        Intent i = new Intent(volunteerhome.this , editvolunteer.class);
        startActivity(i);
    }



}
