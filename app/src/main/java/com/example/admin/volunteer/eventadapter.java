package com.example.admin.volunteer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

/**
 * Created by Admin on 18-04-2017.
 */

public class eventadapter extends RecyclerView.Adapter<event_viewholder> {

      JSONArray json;
      Activity  ac  ;

       public eventadapter (JSONArray job , Activity a ) {

            json = job;
            ac = a;

    }
    @Override
    public event_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        event_viewholder view =new event_viewholder(LayoutInflater.from(ac).inflate(R.layout.eventscell,parent,false));
        return view;



    }

    @Override
    public void onBindViewHolder(event_viewholder holder, int position) {

        try {
            final JSONObject job = (JSONObject) json.get(position);


            holder.date.setText(job.getString("date"));
            holder.name.setText(job.getString("name"));
            holder.location.setText(job.getString("location"));
            holder.description.setText(job.getString("description"));

            holder.events_cell_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ac , Event_details.class);
                    try {
                        i.putExtra("name" , job.getString("name"));
                        i.putExtra("location" , job.getString("location"));
                        i.putExtra("description" , job.getString("description"));
                        i.putExtra("date" , job.getString("date"));
                        i.putExtra("event_id" , job.getString("event_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ac.startActivity(i);

                }
            });
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
