package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;

public class ReminderPersonalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Nullable
    private ItemTapListener mTapListener;

    public TextView titleReminderPersonalView, dateViewPersonal, timeViewPersonal;
    public ImageView imgCheckRmPersonal;


    public ReminderPersonalViewHolder(@NonNull View itemViews, @Nullable ItemTapListener itemTapListener) {
        super(itemViews);
        mTapListener = itemTapListener;
        titleReminderPersonalView = itemViews.findViewById(R.id.titleReminderPersonal);
        dateViewPersonal = itemViews.findViewById(R.id.viewdatePersonal);
        timeViewPersonal = itemViews.findViewById(R.id.viewtimePersonal);
        imgCheckRmPersonal = itemViews.findViewById(R.id.checkemptyPersonal);
        itemViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTapListener.onItemTap(v, getAdapterPosition());
            }
        });



    }

    @Override
    public void onClick(View v) {
        if(mTapListener == null) return;
        mTapListener.onItemTap(v, getAdapterPosition());
    }
}
