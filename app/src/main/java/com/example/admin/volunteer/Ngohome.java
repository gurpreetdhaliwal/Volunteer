package com.example.admin.volunteer;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class Ngohome extends AppCompatActivity {
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngohome);
        fm = getSupportFragmentManager();


        FragmentTransaction ft = fm.beginTransaction();
        Home cm = new Home();
        ft.replace(R.id.frame_id, cm);
        ft.commit();

    }
    public void   openEventsngo(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        Eventsngo cm = new Eventsngo();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void openaddngoevents(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        addngoevents cm = new addngoevents();
        ft. replace (R.id.frame_id, cm);
        ft.commit();
    }

    public void openaccountngo(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        accountngo cm = new accountngo();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void open_profile(View v)
    {
        Intent i = new Intent(Ngohome.this , editngo.class);
        startActivity(i);
    }

    public void open_viewfeedback(View v)
    {
        Intent i = new Intent(Ngohome.this , viewfeedback.class);
        startActivity(i);
    }










}




