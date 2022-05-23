package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.NoteModel;
import com.abra.timecraft.models.Reminder;

import java.util.ArrayList;

public class ReminderCasaAdapter extends RecyclerView.Adapter<ReminderCasaViemHolder> {


    private ArrayList<Reminder> rmCasa;

    @Nullable
    private final ItemTapListener mTapListener;


    public ReminderCasaAdapter(@NonNull ArrayList rmCasas , @Nullable ItemTapListener tapListener ){
      this.rmCasa = rmCasas;
      mTapListener  = tapListener;
    }

    @NonNull
    @Override
    public ReminderCasaViemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_remindercasa, parent, false);
        ReminderCasaViemHolder viewHolder = new ReminderCasaViemHolder(itemView, mTapListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReminderCasaViemHolder holder, final int position) {
        Reminder rm = rmCasa.get(position);

        holder.titleReminderCasaView.setText(rm.getTitutloReco());
        holder.dateView.setText(rm.getDay() + "/" + rm.getMonth() + "/" + rm.getYear());

        String period = "AM";
        if(rm.getHour() > 12) {
            rm.hour = rm.getHour() - 12;
            period = "PM";
        }
        holder.timeView.setText(rm.getHour() + ":" + rm.getMinute() + " " + period);

        holder.imgCheckRm.setTag("activado");
        holder.imgCheckRm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if (holder.imgCheckRm.getTag() == "activado") {
                    holder.imgCheckRm.setBackgroundResource(R.drawable.ic_baseline_check_box_24);
                holder.imgCheckRm.setTag("deactivado");
                mTapListener.onItemTap(v, position);
            }else {
                    holder.imgCheckRm.setTag("activado");
                    holder.imgCheckRm.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_24);
                     mTapListener.onItemTap(v, -1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rmCasa.size();
    }
}