package com.example.admin.volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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

import static com.example.admin.volunteer.R.id.confirm_et;

public class RegisterVolunteer extends AppCompatActivity

   {


    private EditText name_et, mobile_et, email_et, pass_et, confirm_pass, education_et, city_et, address_et;
    Button submit_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_volunteer);

        name_et = (EditText) findViewById(R.id.name_et);
        mobile_et = (EditText) findViewById(R.id.mobile_et);
        email_et = (EditText) findViewById(R.id.email_et);
        pass_et = (EditText) findViewById(R.id.password_et);
        confirm_pass = (EditText) findViewById(confirm_et);
        education_et = (EditText) findViewById(R.id.education_et);
        city_et = (EditText) findViewById(R.id.city_id);
        address_et = (EditText) findViewById(R.id.address_et);
        submit_btn = (Button) findViewById(R.id.submit_btn);

        View.OnClickListener Btn_click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_et.getText().toString();
                String mobile = mobile_et.getText().toString();
                String email = email_et.getText().toString();
                String pass = pass_et.getText().toString();
                String confirm = confirm_pass.getText().toString();
                String education = education_et.getText().toString();
                String city = city_et.getText().toString();
                String address = address_et.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(RegisterVolunteer.this, "enter the name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mobile.length()<10) {
                    Toast.makeText(RegisterVolunteer.this, "re-enter the mobile ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( ! Patterns.EMAIL_ADDRESS.matcher(email).matches())  {
                    Toast.makeText(RegisterVolunteer.this, "enter valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() <8) {
                    Toast.makeText(RegisterVolunteer.this, "enter atleast eight digit", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirm.equals("")) {
                    Toast.makeText(RegisterVolunteer.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (education.equals("")) {
                    Toast.makeText(RegisterVolunteer.this, "enter the education", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (city.equals("")) {
                    Toast.makeText(RegisterVolunteer.this, "enter the city", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (address.equals("")) {
                    Toast.makeText(RegisterVolunteer.this, "enter the address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( ! confirm.equals(pass))
                {
                    Toast.makeText(RegisterVolunteer.this, "password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject json = new JSONObject();
                try {
                    json.put("n", name);
                    json.put("m", mobile);
                    json.put("e", email);
                    json.put("p", pass);
                    json.put("d", education);
                    json.put("c", city);
                    json.put("a", address);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest req = new JsonObjectRequest("http://"+ipaddress.ip+"/volunteer/user.php", json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       try{
                           if(response.getString("key").equals("0"))
                           {
                               Toast.makeText(RegisterVolunteer.this ,"email already exist" , Toast.LENGTH_SHORT).show();

                           }
                           else if(response.getString("key").equals("1")) {
                               Toast.makeText(RegisterVolunteer.this ,"done" , Toast.LENGTH_SHORT).show();

                               Intent i = new Intent(RegisterVolunteer.this ,volunteerhome.class);
                               startActivity(i);

                               finish();

                           }

                           else {
                               Toast.makeText(RegisterVolunteer.this ,"error" , Toast.LENGTH_SHORT).show();

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

                AppController app = new AppController(RegisterVolunteer.this);

                app.addToRequestQueue(req);

            }
        };
        submit_btn.setOnClickListener(Btn_click);


    }

    public void cross_btn(View v) {
        finish();
    }


}