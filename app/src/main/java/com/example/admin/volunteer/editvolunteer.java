package com.example.admin.volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;

import android.view.View;
import android.widget.EditText;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class editvolunteer extends AppCompatActivity {
    EditText name_et, mobile_et, email_et, education_et, city_et, address_et,causes_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editvolunteer);

        name_et = (EditText) findViewById(R.id.name_et);
        mobile_et = (EditText) findViewById(R.id.mobile_et);
        education_et = (EditText) findViewById(R.id.education_et);
        city_et = (EditText) findViewById(R.id.city_et);
        email_et = (EditText) findViewById(R.id.email_et);
        address_et = (EditText) findViewById(R.id.address_et);
        causes_et = (EditText) findViewById(R.id.causes_et);


        name_et.setText(getIntent().getStringExtra("name_key"));
        mobile_et.setText(getIntent().getStringExtra("mobile_key"));
        education_et.setText(getIntent().getStringExtra("education_key"));
        city_et.setText(getIntent().getStringExtra("city_key"));
        address_et.setText(getIntent().getStringExtra("address_key"));
        causes_et.setText(getIntent().getStringExtra("causes_key"));

        get_values();


    }

    public void update_values(View v) {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("volunteer_info", MODE_PRIVATE);
        String email = sp.getString("saved_email", "");

        String mobile_s = mobile_et.getText().toString();
        String education_s = education_et.getText().toString();
        String city_s = city_et.getText().toString();
        String address_s = address_et.getText().toString();
        String name_s = name_et.getText().toString();
        String causes_s = causes_et.getText().toString();





        try {
            job.put("email_key", email);
            job.put("mobile_key", mobile_s);
            job.put("education_key", education_s);
            job.put("city_key", city_s);
            job.put("address_key", address_s);
            job.put("name_key", name_s);
            job.put("causes_key", causes_s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        JsonObjectRequest obreq = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/update_volunteer_profile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });

        obreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(editvolunteer.this);

        app.addToRequestQueue(obreq);
    }

    public void get_values() {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("volunteer_info", MODE_PRIVATE);
        String email = sp.getString("saved_email", "");

        try {
            job.put("email_key", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest obreq = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/volunteer_profile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray arr = response.getJSONArray("key");

                    JSONObject job_response = (JSONObject) arr.get(0);

                    name_et.setText(job_response.getString("name"));
                    mobile_et.setText(job_response.getString("mobile"));
                    email_et.setText(job_response.getString("email"));
                    education_et.setText(job_response.getString("education"));
                    city_et.setText(job_response.getString("city"));
                    address_et.setText(job_response.getString("address"));
                    causes_et.setText(job_response.getString("causes"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });

        obreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(editvolunteer.this);

        app.addToRequestQueue(obreq);
    }


}




