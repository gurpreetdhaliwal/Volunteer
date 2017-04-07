package com.example.admin.volunteer;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import org.json.JSONException;import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class SignInVolunteer extends AppCompatActivity {
    EditText email_et, password_et;
    Button sign_in_btn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_volunteer);



        email_et = (EditText) findViewById(R.id.email);
        password_et = (EditText) findViewById(R.id.password);
        sign_in_btn = (Button) findViewById(R.id.sign_in_btn);

    }


    public void SignUp(View v) {

        Intent i = new Intent(SignInVolunteer.this, RegisterVolunteer.class);
        startActivity(i);
    }

    public void SignIn(View v){
            String email = email_et.getText().toString();

            String password = password_et.getText().toString();


            if (email.equals("")) {
                Toast.makeText(SignInVolunteer.this, "please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.equals("")) {
                Toast.makeText(SignInVolunteer.this, "please enter your password", Toast.LENGTH_SHORT).show();
                return;
            }

            JSONObject json = new JSONObject();

            try {
                json.put("e", email);
                json.put("p", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest req = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/volunteerlogin.php", json,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if (response.getString("key").equals("done")) {

                                    Intent i =new Intent(SignInVolunteer.this , volunteethome.class);

                                     startActivity(i);

                                } else {
                                    Toast.makeText(SignInVolunteer.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            req.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

            AppController app = new AppController(SignInVolunteer.this);

            app.addToRequestQueue(req);

        }

    };








