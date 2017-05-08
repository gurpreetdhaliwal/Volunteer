package com.example.admin.volunteer;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Ngo_event_details extends AppCompatActivity {

    TextView event_name , event_date , event_location , event_details , joining_member ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_event_details);

        event_name = (TextView) findViewById(R.id.event_name_id);

        event_date = (TextView) findViewById(R.id.event_date_id);

        event_location = (TextView) findViewById(R.id.event_location_id);

        event_details = (TextView) findViewById(R.id.event_description_id);

        joining_member = (TextView) findViewById(R.id.event_joining_id);

        event_name.setText(getIntent().getStringExtra("name"));

        event_location.setText(getIntent().getStringExtra("location"));

        event_date.setText(getIntent().getStringExtra("date"));

        event_details.setText(getIntent().getStringExtra("description"));

        get_joining_event();
    }


    public void get_joining_event()
    {
        JSONObject json = new JSONObject();




        try {

            json.put("event_id", getIntent().getStringExtra("event_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/join_event_members.php", json,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                          joining_member.setText( response.getString("num_user") );




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

        AppController app = new AppController(Ngo_event_details.this);

        app.addToRequestQueue(req);
    }
}
