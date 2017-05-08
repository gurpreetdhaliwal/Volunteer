package com.example.admin.volunteer;



import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;

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
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;



import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


public class addngoevents extends Fragment {


     String loc;
    EditText name;

    EditText description;

    Button add;

    EditText date ;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_addngoevents, container, false);


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("", "Place: " + place.getName());

                loc = place.getAddress().toString();


            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status);
            }
        });
        date = (EditText) v.findViewById(R.id.date);
        name = (EditText) v.findViewById(R.id.name);

        description = (EditText) v.findViewById(R.id.description);
        add = (Button) v.findViewById(R.id.add_btn);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_data();
            }
        };

        add.setOnClickListener(click);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_date();
            }
        });


        return v;
    }



    public void add_data() {


        String Name = name.getText().toString();

        String Description = description.getText().toString();
        String date_s = date.getText().toString();


        if (Name.equals("")) {
            Toast.makeText(getActivity(), "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Description.equals("")) {
            Toast.makeText(getActivity(), "enter  description", Toast.LENGTH_SHORT).show();
            return;
        }

        if(date_s.equals(""))
        {
            Toast.makeText(getActivity(), "enter  date ", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject json = new JSONObject();


        SharedPreferences sp = getActivity().getSharedPreferences("ngo_info", MODE_PRIVATE);
        String ngoid = sp.getString("ngo_id", "");


        try {
            json.put("n", Name);

            json.put("d", Description);
            json.put("ngo_id", ngoid);
            json.put("t" , date_s);
            json.put("l",loc);


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
    public void select_date ()

    {
        Calendar mcurrentDate = Calendar.getInstance();
        int year=mcurrentDate.get(Calendar.YEAR);
        int month=mcurrentDate.get(Calendar.MONTH);
        int day=mcurrentDate.get(Calendar.DAY_OF_MONTH);



        final DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int day) {

                String date_s = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);

                date.setText(date_s);


            }
        }, year, month, day);
        mDatePicker.setTitle("Please select date");

        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());

        mDatePicker.show();

    }


}







