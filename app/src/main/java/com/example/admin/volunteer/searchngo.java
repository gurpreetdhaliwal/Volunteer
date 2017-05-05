package com.example.admin.volunteer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class searchngo extends AppCompatActivity {

    TextView ngo_name , ngo_cause , ngo_contact, ngo_email, ngo_address ;

    LinearLayout search_result ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchngo);

        ngo_name =(TextView)findViewById(R.id.ngo_name_id);
        ngo_cause =(TextView)findViewById(R.id.ngo_cause_id);
        ngo_contact =(TextView)findViewById(R.id.ngo_contact_id);
        ngo_email =(TextView)findViewById(R.id.ngo_email_id);
        ngo_address =(TextView)findViewById(R.id.ngo_address_id);

        search_result = (LinearLayout) findViewById(R.id.search_result);




    }

    public void search_ngo(View view) {

        JSONObject jobj = new JSONObject();

        final EditText ngo_name = (EditText) findViewById(R.id.enterngo);
        try {
            jobj.put("name" , ngo_name.getText().toString() );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest job = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/search_ngo_name.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                    search_result.setVisibility(View.VISIBLE);

                    JSONObject job = response.getJSONObject("key");
                    ngo_name.setText(job.getString("name"));
                    ngo_cause.setText(job.getString("causes"));
                    ngo_contact.setText(job.getString("phn"));
                    ngo_email.setText(job.getString("email"));
                    ngo_address.setText(job.getString("address"));


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

        job.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(searchngo.this);

        app.addToRequestQueue(job);


    }
}
