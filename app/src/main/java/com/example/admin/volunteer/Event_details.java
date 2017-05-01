package com.example.admin.volunteer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Event_details extends AppCompatActivity {

    private TextView event_name , description , date , location;

    private Button joining_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        event_name = (TextView) findViewById(R.id.name);

        description = (TextView) findViewById(R.id.description);

        date = (TextView) findViewById(R.id.date);

        location = (TextView) findViewById(R.id.location);

        event_name.setText(getIntent().getStringExtra("name"));

        description.setText(getIntent().getStringExtra("description"));

       date.setText(getIntent().getStringExtra("date"));

        location.setText(getIntent().getStringExtra("location"));

        joining_btn = (Button) findViewById(R.id.join_btn);

        get_joining_status();

    }

    public void join_event(final View view) {


        if( ((Button) view).getText().toString().toLowerCase().equals("join")  ) {


            JSONObject json = new JSONObject();

            SharedPreferences sp = getSharedPreferences("volunteer_info", MODE_PRIVATE);


            try {
                json.put("email", sp.getString("saved_email", ""));
                json.put("event_id", getIntent().getStringExtra("event_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest req = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/join_event.php", json,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if (response.getString("key").equals("done")) {

                                    Toast.makeText(Event_details.this, "event joined successfully", Toast.LENGTH_SHORT).show();
                                    ((Button) view).setText("un-join");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            req.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

            AppController app = new AppController(Event_details.this);

            app.addToRequestQueue(req);

        }

        else {

            JSONObject json = new JSONObject();

            SharedPreferences sp = getSharedPreferences("volunteer_info", MODE_PRIVATE);


            try {
                json.put("email", sp.getString("saved_email", ""));
                json.put("event_id", getIntent().getStringExtra("event_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest req = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/unjoin_event.php", json,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if (response.getString("key").equals("done")) {

                                    Toast.makeText(Event_details.this, "event unjoined successfully", Toast.LENGTH_SHORT).show();
                                    ((Button) view).setText("join");

                                }
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

            req.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

            AppController app = new AppController(Event_details.this);

            app.addToRequestQueue(req);

        }
    }

    public void get_joining_status()
    {

        JSONObject json = new JSONObject();

        SharedPreferences sp = getSharedPreferences("volunteer_info", MODE_PRIVATE);


        try {
            json.put("email", sp.getString("saved_email", ""));
            json.put("event_id", getIntent().getStringExtra("event_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/get_join_event.php", json,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        System.out.println(response);

                        try {
                            if (response.getString("key").equals("done")) {

                                Toast.makeText(Event_details.this, "event joined already", Toast.LENGTH_SHORT).show();

                                joining_btn.setText("un-join");


                            }
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

        req.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(Event_details.this);

        app.addToRequestQueue(req);
    }
}
