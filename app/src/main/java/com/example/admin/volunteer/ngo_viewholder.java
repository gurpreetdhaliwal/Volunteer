package com.example.admin.volunteer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Admin on 19-04-2017.
 */

public class ngo_viewholder extends RecyclerView.ViewHolder {

    TextView name ,date ,location, description ;
    public ngo_viewholder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.name);
        date=(TextView)itemView.findViewById(R.id.date);
        location=(TextView)itemView.findViewById(R.id.location);
        description=(TextView)itemView.findViewById(R.id.description);

    }
}

