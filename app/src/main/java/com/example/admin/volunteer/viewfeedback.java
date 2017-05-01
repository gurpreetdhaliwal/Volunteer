package com.example.admin.volunteer;

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


public class viewfeedback extends AppCompatActivity {

    RecyclerView recycle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfeedback);

        recycle = (RecyclerView) findViewById(R.id.recyclerview);
        get_feedback();
        return ;



    }

    public void get_feedback(){
        JsonObjectRequest job = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/viewfeedback.php", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsarr =  response.getJSONArray("key");

                    viewfeed_adapter ad = new viewfeed_adapter(jsarr , viewfeedback.this);

                    recycle.setLayoutManager(new LinearLayoutManager(viewfeedback.this,LinearLayoutManager.VERTICAL,false));

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

        AppController app = new AppController(viewfeedback.this);

        app.addToRequestQueue(job);
    }

}


