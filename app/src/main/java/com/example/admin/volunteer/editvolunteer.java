package com.example.admin.volunteer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;

import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class editvolunteer extends AppCompatActivity {
    EditText name_et, mobile_et,  email_et, education_et , city_et, address_et;


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

        get_values();






    }
    public void get_values()
    {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("volunteer_info" , MODE_PRIVATE);
        String email = sp.getString("saved_email" , "");

        try {
            job.put("email_key", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest obreq = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/volunteer_profile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray arr =  response.getJSONArray("key");

                    JSONObject job_response = (JSONObject) arr.get(0);

                    name_et.setText( job_response.getString("name") );
                    mobile_et.setText( job_response.getString("mobile"));
                    email_et.setText(job_response.getString("email"));
                    education_et.setText( job_response.getString("education"));
                    city_et.setText(job_response.getString("city"));
                    address_et.setText(job_response.getString("address"));

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

        obreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 , 2));

        AppController app = new AppController(editvolunteer.this);

        app.addToRequestQueue(obreq);
    }
}


