package com.example.admin.volunteer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;


/**
 * Created by Admin on 26-04-2017.
 */

public class momentadapter  extends RecyclerView.Adapter<moment_viewholder> {

        JSONArray json;
        Activity  ac  ;

public momentadapter (JSONArray job , Activity a ) {

        json = job;
        ac = a;

        }
@Override
public moment_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        moment_viewholder view =new moment_viewholder(LayoutInflater.from(ac).inflate(R.layout.momentcell,parent,false));
        return view;



        }

@Override
public void onBindViewHolder(moment_viewholder holder, int position) {

        try {
        JSONObject job = (JSONObject) json.get(position);



        holder.name.setText(job.getString("name"));
            holder.description.setText(job.getString("description"));

               Bitmap bmp =  StringToBitMap(job.getString("image"));

                holder.moment_image.setImageBitmap(bmp);
        } catch (JSONException e) {
        e.printStackTrace();
        }
        }







@Override
public int getItemCount(){return  json.length();
}

        public Bitmap StringToBitMap(String encodedString){
                try {
                        byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
                        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                        return bitmap;
                } catch(Exception e) {
                        e.getMessage();
                        return null;
                }
        }

}
