package com.example.admin.volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgetpassword extends AppCompatActivity {
    EditText email_et;
    Button next_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        email_et = (EditText) findViewById(R.id.email_et);
        next_btn =(Button) findViewById(R.id.next_btn);


    }

        public void next_btn (View v){

             String email = email_et.getText().toString();
             int randompin = (int) (Math.random()*9000);

            Intent i = new Intent(forgetpassword.this ,onetimepassword.class);

            i.putExtra("email_key",email);
            i.putExtra("pin_key", String.valueOf(randompin));

            startActivity(i);
            finish();
    }
    }

