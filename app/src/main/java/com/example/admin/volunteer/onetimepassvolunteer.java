package com.example.admin.volunteer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class onetimepassvolunteer extends AppCompatActivity {
    EditText otp_et;
    String pin ;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onetimepassvolunteer);

        otp_et =(EditText)findViewById(R.id.otp_et);

        email = getIntent().getStringExtra("email_key");
        pin =getIntent().getStringExtra("pin_key");

        Toast.makeText(onetimepassvolunteer.this, pin, Toast.LENGTH_SHORT).show();

    }
    public void verify (View v){
        String otp = otp_et.getText().toString();

        if (otp.equals(pin)){
            Toast.makeText(onetimepassvolunteer.this, "code matched" ,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(onetimepassvolunteer.this,newpassvolunteer.class);

            i.putExtra("email_key" ,email);
            startActivity(i);
            finish();

        }

        else {
            Toast.makeText(onetimepassvolunteer.this,"code do not match", Toast.LENGTH_SHORT).show();
        }
    }
}




