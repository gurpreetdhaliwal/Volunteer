package com.example.admin.volunteer;

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




public class addvolunteer extends Fragment {

    EditText name, description;
    Button post;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_addvolunteer, container, false);

        name = (EditText) v.findViewById(R.id.namen);

        description = (EditText) v.findViewById(R.id.descriptiond);

        post = (Button) v.findViewById(R.id.post_btn);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_data();
            }
        };

        post.setOnClickListener(click);


        return v;
    }

    public void post_data() {

        String Name = name.getText().toString();
        String Description = description.getText().toString();

        if (Name.equals("")) {
            Toast.makeText(getActivity(), "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Description.equals("")) {
            Toast.makeText(getActivity(), "enter  description", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject json = new JSONObject();


        SharedPreferences sp = getActivity().getSharedPreferences("volunteer_info", MODE_PRIVATE);
        String volunteeremail = sp.getString("saved_email", "");

        try {
            json.put("n", Name);
            json.put("d", Description);
            json.put("saved_email", volunteeremail);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(json);
        JsonObjectRequest req = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/volunteermoment.php", json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("key").equals("0")) {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

                    } else if (response.getString("key").equals("1")) {
                        Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

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

        AppController app = new AppController(getActivity());

        app.addToRequestQueue(req);

    }


};


