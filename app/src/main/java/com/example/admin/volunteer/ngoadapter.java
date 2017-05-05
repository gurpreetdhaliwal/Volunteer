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



/**
 * Created by Admin on 19-04-2017.
 */

public class ngoadapter extends RecyclerView.Adapter<ngo_viewholder> {

    JSONArray json;
    Activity  ac  ;

    public ngoadapter (JSONArray job , Activity a ) {

        json = job;
        ac = a;

    }
    @Override
    public ngo_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        ngo_viewholder view =new ngo_viewholder(LayoutInflater.from(ac).inflate(R.layout.ngocell,parent,false));
        return view;



    }

    @Override
    public void onBindViewHolder(ngo_viewholder holder, int position) {

        try {
           final   JSONObject job = (JSONObject) json.get(position);


            holder.date.setText(job.getString("date"));
            holder.name.setText(job.getString("name"));
            holder.location.setText(job.getString("location"));
            holder.description.setText(job.getString("description"));

            holder.cell_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ac , Ngo_event_details.class);

                    try {
                        i.putExtra("date" , job.getString("date") );
                        i.putExtra("name" , job.getString("name") );
                        i.putExtra("description" , job.getString("description"));
                        i.putExtra("location" , job.getString("location"));
                        i.putExtra("event_id" , job.getString("event_id"));

                        ac.startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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


