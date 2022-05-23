package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;


public class ReminderCasaViemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Nullable
    private ItemTapListener mTapListener;

    public TextView titleReminderCasaView, dateView, timeView;
    public ImageButton imgCheckRm;

    public ReminderCasaViemHolder(@NonNull View itemView, @Nullable ItemTapListener tapListener) {
        super(itemView);
        mTapListener = tapListener;
        titleReminderCasaView = itemView.findViewById(R.id.titleReminderCasa);
        dateView = itemView.findViewById(R.id.viewdate);
        timeView = itemView.findViewById(R.id.viewtimeCasa);
        imgCheckRm = itemView.findViewById(R.id.checkemptyCasa);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { mTapListener.onItemTap(v,getAdapterPosition());
            }
          }
        );
    }

    public void onClick(View view) {
        if(mTapListener == null) return;
        mTapListener.onItemTap(view, getAdapterPosition());
    }
}

