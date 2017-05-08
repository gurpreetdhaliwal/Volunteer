package com.example.admin.volunteer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;



/**
 * Created by Admin on 21-04-2017.
 */

public class viewfeed_adapter   extends RecyclerView.Adapter<view_ngofeedholder> {

    JSONArray json;
    Activity  ac  ;

    public viewfeed_adapter (JSONArray job , Activity a ) {

        json = job;
        ac = a;

    }
    @Override
    public view_ngofeedholder onCreateViewHolder(ViewGroup parent, int viewType) {

        view_ngofeedholder view =new view_ngofeedholder(LayoutInflater.from(ac).inflate(R.layout.viewfeedcell,parent,false));
        return view;



    }

    @Override
    public void onBindViewHolder(view_ngofeedholder holder, int position) {

        try {
            JSONObject job = (JSONObject) json.get(position);


            holder.date.setText(job.getString("date"));
            holder.event_name.setText(job.getString("date"));
            holder.ngoname.setText(job.getString("ngo_name"));
            holder.message.setText(job.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }







    @Override
    public int getItemCount()
    {
        return  json.length();
    }
}


