package com.example.admin.volunteer;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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



public class RegisterNgo extends AppCompatActivity {
    EditText name_et, causes_et, phn_et, org_et, email_et, password_et, confirm_et, city_et, address_et;
    Button submit_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ngo);

        name_et = (EditText) findViewById(R.id.name_et);
        causes_et = (EditText) findViewById(R.id.causes_et);
        phn_et = (EditText) findViewById(R.id.phn_et);
        org_et = (EditText) findViewById(R.id.org_et);
        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.password_et);
        confirm_et = (EditText) findViewById(R.id.confirm_et);
        city_et = (EditText) findViewById(R.id.city_et);
        address_et = (EditText) findViewById(R.id.address_et);
        submit_btn = (Button) findViewById(R.id.submit_btn);

        View.OnClickListener Btn_click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_et.getText().toString();
                String causes = causes_et.getText().toString();
                String phn = phn_et.getText().toString();
                String org = org_et.getText().toString();
                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                String confirm = confirm_et.getText().toString();
                String address = address_et.getText().toString();
                String city = city_et.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (causes.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the causes", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phn.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (org.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the organization", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirm.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (address.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (city.equals("")) {
                    Toast.makeText(RegisterNgo.this, "enter the city", Toast.LENGTH_SHORT).show();
                    return;
                }

                if( !confirm.equals(password))
                {
                    Toast.makeText(RegisterNgo.this, "password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject json = new JSONObject();
                try {
                    json.put("n", name);
                    json.put("c",causes);
                    json.put("p", phn);
                    json.put("o", org);
                    json.put("e", email);
                    json.put("s", password);
                    json.put("a", address);
                    json.put("t", city);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(json);

                JsonObjectRequest job = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/ngoregister.php", json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getString("key").equals("0"))
                            {
                                Toast.makeText(RegisterNgo.this ,"email already exist" , Toast.LENGTH_SHORT).show();

                            }
                            else if(response.getString("key").equals("1")) {

                                Intent i = new Intent(RegisterNgo.this , Ngohome.class);

                                startActivity(i);
                                finish();
                                Toast.makeText(RegisterNgo.this ,"done" , Toast.LENGTH_SHORT).show();

                            }

                            else {
                                Toast.makeText(RegisterNgo.this ,"error" , Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        {
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        System.out.println(error);
                    }
                });

                job.setRetryPolicy(new DefaultRetryPolicy(20000,2,2));

                AppController app = new AppController(RegisterNgo.this);

                app.addToRequestQueue(job);


            }






    };


    submit_btn.setOnClickListener(Btn_click);
}

    public void cross_btn(View v) {

        finish();


    }
}




