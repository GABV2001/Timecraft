
package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.Reminder;

import java.util.ArrayList;

public class ReminderReunionAdapter extends RecyclerView.Adapter<ReminderReunionViewHolder>{

    private ArrayList<Reminder> rmReunion;

    @Nullable
    private final ItemTapListener mTapListener;


    public ReminderReunionAdapter(@NonNull ArrayList rmReunions, @Nullable ItemTapListener tapListen ) {
        this.rmReunion = rmReunions;
        mTapListener = tapListen;

    }

    @NonNull
    @Override
    public ReminderReunionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_reminderreunion, parent, false);
        ReminderReunionViewHolder viewHolder = new ReminderReunionViewHolder(itemView, mTapListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReminderReunionViewHolder holder, final int position) {
        Reminder rm = rmReunion.get(position);

        holder.titleReminderReunionView.setText(rm.getTitutloReco());
        holder.dateViewReunion.setText(rm.getDay() + "/" + rm.getMonth() + "/" + rm.getYear());

        String period = "AM";
        if(rm.getHour() > 12) {
            rm.hour = rm.getHour() - 12;
            period = "PM";
        }
        holder.timeViewReunion.setText(rm.getHour() + ":" + rm.getMinute() + " " + period);

        holder.imgCheckRmReunion.setTag("activado");
        holder.imgCheckRmReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if (holder.imgCheckRmReunion.getTag() == "activado") {
                holder.imgCheckRmReunion.setBackgroundResource(R.drawable.ic_baseline_check_box_24);
                holder.imgCheckRmReunion.setTag("deactivado");
                mTapListener.onItemTap(v, position);
            }else {
                holder.imgCheckRmReunion.setTag("activado");
                holder.imgCheckRmReunion.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_24);
                mTapListener.onItemTap(v, -1);
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rmReunion.size();
    }
}

