package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @Nullable
    private ItemTapListener mTapListener;

    public TextView titleNote, dateViewNote, timeViewNote;
    public ImageButton imgChecNote;

       public NoteViewHolder(View itemView, ItemTapListener itemTapListener) {
        super(itemView);
        mTapListener = itemTapListener;
        titleNote = itemView.findViewById(R.id.titleNote);
        dateViewNote = itemView.findViewById(R.id.viewdateNote);
        imgChecNote = itemView.findViewById(R.id.checkemptyNote);
        timeViewNote = itemView.findViewById(R.id.viewtimeNote);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTapListener.onItemTap(v, getAdapterPosition());
            }
        });

    }

    public void onClick(View view) {
        if(mTapListener == null) return;
        mTapListener.onItemTap(view, getAdapterPosition());
    }

    public void getItemClickListener(ItemTapListener itemClickListener){
        this.mTapListener =itemClickListener;
    }
}
