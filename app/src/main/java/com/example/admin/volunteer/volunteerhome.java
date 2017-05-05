package com.example.admin.volunteer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by Admin on 30-03-2017.
 */

public class volunteerhome extends AppCompatActivity {
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteerhome);
        fm = getSupportFragmentManager();


        FragmentTransaction ft = fm.beginTransaction();
        Home cm = new Home();
        ft.replace(R.id.frame_id, cm);
        ft.commit();

    }

    public void openhome(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        Home cm = new Home();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void openngosearchoption(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        ngosearchoption cm = new ngosearchoption();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void openevents(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        events cm = new events();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void openaccount(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        account cm = new account();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void openaddvolunteer(View v) {

        FragmentTransaction ft = fm.beginTransaction();
        addvolunteer cm = new addvolunteer();
        ft.replace(R.id.frame_id, cm);
        ft.commit();
    }

    public void open_volunteer_profile(View v) {
        Intent i = new Intent(volunteerhome.this, editvolunteer.class);
        startActivity(i);
    }

    public void open_addfeedback(View v) {
        Intent i = new Intent(volunteerhome.this, addfeedback.class);
        startActivity(i);
    }

       public void opensearchngo(View v) {
        Intent i = new Intent(volunteerhome.this, searchngo.class);
        startActivity(i);
    }







    public void logout(View v) {
        Intent i = new Intent(volunteerhome.this, MainActivity.class);
        startActivity(i);
    }
    public void rate(View v){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }



    public void add_image(View v) {

        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");

        //File file = new File(Environment.getExternalStorageDirectory(),
        //      counter+".jpg");
        //Uri photoPath = Uri.fromFile(file);
        // i.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);
        startActivityForResult(i, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100 && data != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap2 = decodeUri(volunteerhome.this, filePath, 700);
                addvolunteer.moment_image_string = getStringImage(bitmap2);
                //Setting the Bitmap to ImageView
                addvolunteer.moment_image.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public String getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

}
