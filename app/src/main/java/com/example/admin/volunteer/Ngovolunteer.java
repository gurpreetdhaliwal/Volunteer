package com.example.admin.volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ngovolunteer extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngovolunteer);

    }
    public void RegisterNgo(View v){
        Intent i=new Intent(Ngovolunteer.this,SignInNgo.class);
         startActivity(i);

    }
    public void RegisterVolunteer(View v){
        Intent i=new Intent(Ngovolunteer.this,SignInVolunteer.class);
        startActivity(i);

    }
}
