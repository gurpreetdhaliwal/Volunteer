package com.example.admin.volunteer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class newpassvolunteer extends AppCompatActivity {
    EditText newpassword_et;
    Button setpass_btn;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassvolunteer);

        email = getIntent().getStringExtra("email_key");

        newpassword_et =(EditText)findViewById(R.id.newpassword);
    }

    public void setpass (View v){

        String newpassword =  newpassword_et.getText().toString();

        if (newpassword.length()>8) {
            Toast.makeText(newpassvolunteer.this, "enter  password atleast eight digit", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject json =new JSONObject();


        try {
            json.put("n", newpassword);
            json.put("e",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(json);
      //  Toast.makeText( newpassvolunteer.this ,String.valueOf(json), Toast.LENGTH_SHORT).show();

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/update_passvolunteer.php", json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if(response.getString("key").equals("done"))
                    {
                        Toast.makeText( newpassvolunteer.this ,"password updated successfully" , Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    else {
                        Toast.makeText( newpassvolunteer.this ,"error" , Toast.LENGTH_SHORT).show();

                    }
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

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2 , 2));

        AppController app = new AppController(newpassvolunteer.this);

        app.addToRequestQueue(jobreq);





    }
}


