package com.example.admin.volunteer;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class editngo extends AppCompatActivity {

    EditText name_et, causes_et, phn_et, org_et, email_et,   city_et, address_et;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editngo);



        name_et =( EditText) findViewById(R.id.ngo_et);
        causes_et = (EditText) findViewById(R.id.causes_et);
        phn_et = (EditText) findViewById(R.id.phone_et);
        org_et = (EditText) findViewById(R.id.organization_et);
        email_et = (EditText) findViewById(R.id.email_et);
        city_et = (EditText) findViewById(R.id.city_et);
        address_et = (EditText) findViewById(R.id.address_et);

        name_et.setText(getIntent().getStringExtra("name_key"));
        causes_et.setText(getIntent().getStringExtra("causes_key"));
        phn_et.setText(getIntent().getStringExtra("phn_key"));
        org_et.setText(getIntent().getStringExtra("organization_key"));
        address_et.setText(getIntent().getStringExtra("address_key"));
        city_et.setText(getIntent().getStringExtra("city_key"));



        get_values();


    }


    public void update_values(View v)
    {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("ngo_info" , MODE_PRIVATE);
        String email = sp.getString("saved_email" , "");
        String name_s = name_et.getText().toString();
        String causes_s = causes_et.getText().toString();
        String phn_s = phn_et.getText().toString();
        String organization_s = org_et.getText().toString();
        String city_s = city_et.getText().toString();
        String address_s = address_et.getText().toString();



        try {
            job.put("email_key", email);
            job.put("name_key", name_s);
            job.put("causes_key", causes_s);
            job.put("phn_key", phn_s);
            job.put("organization_key", organization_s);
            job.put("city_key", city_s);
            job.put("address_key", address_s);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/update_ngo_profile.php", job, new Response.Listener<JSONObject>() {
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

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 , 2));

        AppController app = new AppController(editngo.this);

        app.addToRequestQueue(jobreq);
    }



    public void get_values()
    {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("ngo_info" , MODE_PRIVATE);
        String email = sp.getString("saved_email" , "");

        try {
            job.put("email_key", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/get_profile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr =  response.getJSONArray("key");

                    JSONObject job_response = (JSONObject) jarr.get(0);

                   name_et.setText( job_response.getString("name") );
                   causes_et.setText( job_response.getString("causes"));
                   phn_et.setText( job_response.getString("phn"));
                    org_et.setText(job_response.getString("organization"));
                    email_et.setText(job_response.getString("email"));
                    address_et.setText(job_response.getString("address"));
                   city_et.setText(job_response.getString("city"));

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

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 , 2));

        AppController app = new AppController(editngo.this);

        app.addToRequestQueue(jobreq);
    }
}
