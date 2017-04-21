package com.example.admin.volunteer;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Eventsngo extends Fragment {

    RecyclerView recycle ;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_eventsngo, container, false);

        recycle = (RecyclerView) v.findViewById(R.id.recyclerview);
        add_events();
        return v;

    }
    public void add_events(){
        JsonObjectRequest job = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/geteventsvolunteer.php", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsarr =  response.getJSONArray("key");

                            eventadapter ad = new eventadapter(jsarr , getActivity());

                    recycle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    recycle.setAdapter(ad);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        job.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(getActivity());

        app.addToRequestQueue(job);
    }

}


