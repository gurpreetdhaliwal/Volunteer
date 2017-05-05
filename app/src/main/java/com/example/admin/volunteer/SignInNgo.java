package com.example.admin.volunteer;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import org.json.JSONException;import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class SignInNgo extends AppCompatActivity {
    EditText email_et, password_et;
    Button sign_in_btn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_ngo);

        email_et = (EditText) findViewById(R.id.email);
        password_et = (EditText) findViewById(R.id.password);
        sign_in_btn = (Button) findViewById(R.id.sign_in_btn);



    }
     public void forgetpassword(View v){

         Intent i = new Intent(SignInNgo.this ,forgetpassword.class);
         startActivity(i);
     }

    public  void SignUp(View v)
    {
        Intent i = new Intent(SignInNgo.this, RegisterNgo.class);
        startActivity(i);
    }


    public void SignIn(View v) {

            final String email = email_et.getText().toString();

            String password = password_et.getText().toString();


        if ( ! Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(SignInNgo.this, "please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals("")) {
                Toast.makeText(SignInNgo.this, "please enter your password", Toast.LENGTH_SHORT).show();
                return;
            }

            JSONObject json = new JSONObject();

            try {
                json.put("e", email);
                json.put("s", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest req = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/ngologin.php", json,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);

                            try {
                                if (response.getString("key").equals("done")) {

                                    SharedPreferences.Editor sp = getSharedPreferences("ngo_info" , MODE_PRIVATE).edit();
                                    sp.putString("saved_email" , email);
                                    sp.putString("ngo_id",response.getString("id"));
                                    sp.commit();

                                    Intent i =new Intent(SignInNgo.this , Ngohome.class);

                                    startActivity(i);


                                } else {
                                    Toast.makeText(SignInNgo.this, "error", Toast.LENGTH_SHORT).show();
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

            req.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

            AppController app = new AppController(SignInNgo.this);

            app.addToRequestQueue(req);

        }








}



