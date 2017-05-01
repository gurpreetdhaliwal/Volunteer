package com.example.admin.volunteer;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import android.app.Activity;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.ViewGroup;



/**
 * Created by Admin on 20-04-2017.
 */

public class feedadapter  extends RecyclerView.Adapter<feed_viewholder> {

    JSONArray json;
    Activity  ac  ;

    public feedadapter (JSONArray job , Activity a ) {

        json = job;
        ac = a;

    }
    @Override
    public feed_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        feed_viewholder view =new feed_viewholder(LayoutInflater.from(ac).inflate(R.layout.viewfeedcell,parent,false));
        return view;



    }

    @Override
    public void onBindViewHolder(feed_viewholder holder, int position) {

        try {
            JSONObject job = (JSONObject) json.get(position);


            holder.date.setText(job.getString("date"));
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



