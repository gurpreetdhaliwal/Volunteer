package com.example.admin.volunteer;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Admin on 20-04-2017.
 */

public class feed_viewholder extends RecyclerView.ViewHolder {

    TextView ngoname, date, message;

    public feed_viewholder(View itemView) {
        super(itemView);

        ngoname = (TextView) itemView.findViewById(R.id.ngo_name);

        date = (TextView) itemView.findViewById(R.id.datef);


        message = (TextView) itemView.findViewById(R.id.message);

    }
}