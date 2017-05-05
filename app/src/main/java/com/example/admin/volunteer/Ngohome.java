package com.example.admin.volunteer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.DatePicker;
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
        Eventsngo cm = new Eventsngo();
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



    public void share (View v)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the app 'Volunteer' via play store now...";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    public void rate(View v){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }



    public void logout(View v)
    {
        Intent i = new Intent(Ngohome.this , MainActivity.class);
        startActivity(i);
    }


}



















