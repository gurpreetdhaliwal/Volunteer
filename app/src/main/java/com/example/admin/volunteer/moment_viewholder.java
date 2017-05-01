package com.example.admin.volunteer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by Admin on 26-04-2017.
 */

public class moment_viewholder extends RecyclerView.ViewHolder {

    TextView name , description ;

    ImageView moment_image;
    public moment_viewholder (View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.name);
        description=(TextView)itemView.findViewById(R.id.description);

        moment_image = (ImageView) itemView.findViewById(R.id.moment_image);

    }
}
