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

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder>{

    private ArrayList<NoteModel> noteModel;
    @Nullable
    private final ItemTapListener mTapListener;


    public NoteAdapter(@NonNull ArrayList note, @Nullable ItemTapListener itemTapListener ) {
        this.noteModel = note;
        this.mTapListener = itemTapListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_note, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView, mTapListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, final int position) {
        NoteModel nt = noteModel.get(position);
        holder.titleNote.setText(nt.getTitle());
        holder.dateViewNote.setText(nt.getDay() + "/" + nt.getMonth() + "/" + nt.getYear());

      /*  String period = "AM";
        if(nt.getHour() > 12) {
            nt.hour = nt.getHour() - 12;
            period = "PM";
        }*/
       // holder.timeViewNote.setText(nt.getHour() + ":" + nt.getMinute() );

        holder.imgChecNote.setTag("activado");
        holder.imgChecNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if (
                    holder.imgChecNote.getTag() == "activado") {
                holder.imgChecNote.setBackgroundResource(R.drawable.ic_baseline_check_box_24);
                holder.imgChecNote.setTag("deactivado");
                mTapListener.onItemTap(v, position);
            }else {
                holder.imgChecNote.setTag("activado");
                holder.imgChecNote.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_24);
                mTapListener.onItemTap(v, -1);
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  noteModel.size();
    }
}
