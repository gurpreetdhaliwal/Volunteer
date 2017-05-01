package com.example.admin.volunteer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


public class addfeedback extends AppCompatActivity {

    EditText ngo_name,  date, message;

    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfeedback);


        ngo_name = (EditText) findViewById(R.id.ngo_name);

        date = (EditText) findViewById(R.id.datef);


        message = (EditText) findViewById(R.id.message);

        send = (Button) findViewById(R.id.send_btn);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_data();
            }
        };
        send.setOnClickListener(click);


    }

    public void send_data() {


        String Name =  ngo_name.getText().toString();


        String Date = date.getText().toString();
        String Message = message.getText().toString();



        if (Name.equals("")) {
            Toast.makeText(addfeedback.this, "enter the ngo name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Date.equals("")) {
            Toast.makeText(addfeedback.this, "enter the date", Toast.LENGTH_SHORT).show();
            return;
        }


        if (Message.equals("")) {
            Toast.makeText(addfeedback.this, "enter  message", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject json = new JSONObject();

        SharedPreferences sp = addfeedback.this.getSharedPreferences("volunteer_info" , MODE_PRIVATE);
        String volunteeremail =   sp.getString("saved_email","");





        try {
            json.put("n",Name);

            json.put("d", Date);
            json.put("m", Message);
            json.put("saved_email", volunteeremail);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(json);
        JsonObjectRequest req = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/feedback.php ", json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("key").equals("0")) {
                        Toast.makeText(addfeedback.this, "error", Toast.LENGTH_SHORT).show();

                    } else if (response.getString("key").equals("1")) {
                        Toast.makeText(addfeedback.this, "done", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(addfeedback.this, "error", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                {
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);

                    }
                });
        req.setRetryPolicy(new DefaultRetryPolicy(2000, 2, 2));

        AppController app = new AppController(addfeedback.this);

        app.addToRequestQueue(req);

                }

};

