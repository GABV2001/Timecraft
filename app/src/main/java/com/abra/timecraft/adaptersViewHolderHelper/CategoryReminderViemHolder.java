package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;

public class CategoryReminderViemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView tvTituloCatReminder;
    public ImageView imgCategoriaReminder;
    @Nullable
    private final ItemTapListener itemTapListener;

    public CategoryReminderViemHolder(@NonNull View itemView, @Nullable ItemTapListener itemTapListener) {
        super(itemView);
        this.itemTapListener = itemTapListener;
        itemView.setOnClickListener(this);

        tvTituloCatReminder = itemView.findViewById(R.id.tv_categoryReminder);
        imgCategoriaReminder = itemView.findViewById(R.id.img_categoryReminder);
    }


    @Override
    public void onClick(View view) {
        if (itemTapListener == null) return;
        itemTapListener.onItemTap(view, getAdapterPosition());
    }
}
