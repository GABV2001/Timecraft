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

public class ReminderPersonalAdapter extends RecyclerView.Adapter<ReminderPersonalViewHolder>{

    private ArrayList<Reminder> rmPersonal;

    @Nullable
    private final ItemTapListener mTapListener;

    public ReminderPersonalAdapter(@NonNull ArrayList rmPersonals, ItemTapListener tapListener ) {
        this.rmPersonal = rmPersonals;
        mTapListener = tapListener;
    }

    @NonNull
    @Override
    public ReminderPersonalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemViews = inflater.inflate(R.layout.item_reminderpersonal, parent, false);
        ReminderPersonalViewHolder viewHolder = new ReminderPersonalViewHolder(itemViews, mTapListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReminderPersonalViewHolder holder, final int position) {
        Reminder rm = rmPersonal.get(position);
        holder.titleReminderPersonalView.setText(rm.getTitutloReco());
        holder.dateViewPersonal.setText(rm.getDay() + "/" + rm.getMonth() + "/" + rm.getYear());

        String period = "AM";
        if(rm.getHour() > 12) {
            rm.hour = rm.getHour() - 12;
            period = "PM";
        }
        holder.timeViewPersonal.setText(rm.getHour() + ":" + rm.getMinute() + " " + period);

        holder.imgCheckRmPersonal.setTag("activado");
        holder.imgCheckRmPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if (
                    holder.imgCheckRmPersonal.getTag() == "activado") {
                holder.imgCheckRmPersonal.setBackgroundResource(R.drawable.ic_baseline_check_box_24);
                holder.imgCheckRmPersonal.setTag("deactivado");
                mTapListener.onItemTap(v, position);
            }else {
                holder.imgCheckRmPersonal.setTag("activado");
                holder.imgCheckRmPersonal.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_24);
                mTapListener.onItemTap(v, -1);
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rmPersonal.size();
    }
}
