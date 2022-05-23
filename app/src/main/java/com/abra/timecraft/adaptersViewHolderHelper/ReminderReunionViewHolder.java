package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;

public class ReminderReunionViewHolder extends RecyclerView.ViewHolder {

    public TextView titleReminderReunionView, dateViewReunion, timeViewReunion;
    public ImageView imgCheckRmReunion;

    @Nullable
    private ItemTapListener mTapListener;


    public ReminderReunionViewHolder(@NonNull View itemView, @Nullable ItemTapListener itemTapListener) {
        super(itemView);
        mTapListener = itemTapListener;
        titleReminderReunionView = itemView.findViewById(R.id.titleReminderReunion);
        dateViewReunion = itemView.findViewById(R.id.viewdateReunion);
        timeViewReunion = itemView.findViewById(R.id.viewtimeReunion);
        imgCheckRmReunion = itemView.findViewById(R.id.checkemptyReunion);

        itemView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) { mTapListener.onItemTap(v,getAdapterPosition());
                                        }
                                    }
        );
    }
}
