package com.example.admin.volunteer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Admin on 18-04-2017.
 */

public class event_viewholder extends RecyclerView.ViewHolder {

   TextView name ,date ,location, description ;

    LinearLayout events_cell_lay;


    public event_viewholder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.name);
        date=(TextView)itemView.findViewById(R.id.date);
        location=(TextView)itemView.findViewById(R.id.location);
        description=(TextView)itemView.findViewById(R.id.description);

        events_cell_lay = (LinearLayout) itemView.findViewById(R.id.event_cell_id);


    }
}
