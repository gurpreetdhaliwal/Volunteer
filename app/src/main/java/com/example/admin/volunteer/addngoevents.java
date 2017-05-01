package com.example.admin.volunteer;



import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


public class addngoevents extends Fragment {


    EditText date;
    EditText name;
    EditText location;
    EditText description;

    Button add;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_addngoevents, container, false);


        date = (EditText) v.findViewById(R.id.date);
        name = (EditText) v.findViewById(R.id.name);
        location = (EditText) v.findViewById(R.id.location);
        description = (EditText) v.findViewById(R.id.description);



        add = (Button) v.findViewById(R.id.add_btn);
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_data();
            }
        };

        add.setOnClickListener(click);

        return v;
    }


    public void add_data() {

        String Date = name.getText().toString();
        String Name = name.getText().toString();
        String Location = location.getText().toString();
        String Description = description.getText().toString();

        if (Date.equals("")) {
            Toast.makeText(getActivity(), "enter the date", Toast.LENGTH_SHORT).show();
            return;
        }


        if (Name.equals("")) {
            Toast.makeText(getActivity(), "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Location.equals("")) {
            Toast.makeText(getActivity(), "enter the location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Description.equals("")) {
            Toast.makeText(getActivity(), "enter  description", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject json = new JSONObject();


        SharedPreferences sp = getActivity().getSharedPreferences("ngo_info", MODE_PRIVATE);
        String ngoid = sp.getString("ngo_id", "");


        try {
            json.put("n", Name);
            json.put("t", Date);

            json.put("l", Location);
            json.put("d", Description);
            json.put("ngo_id", ngoid);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(json);
        JsonObjectRequest req = new JsonObjectRequest("http://" + ipaddress.ip + "/volunteer/addevents.php", json, new Response.Listener<JSONObject>() {
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
}






