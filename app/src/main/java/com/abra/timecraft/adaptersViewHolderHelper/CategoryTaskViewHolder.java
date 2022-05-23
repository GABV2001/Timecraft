package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;

public class CategoryTaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvTitulCatTask, cantTask;
    public ImageView imgCategoriaTask;
    @Nullable
    private final ItemTapListener mTapListener;

    public CategoryTaskViewHolder(@NonNull View itemView, ItemTapListener mTapListener) {
        super(itemView);
        this.mTapListener = mTapListener;
        itemView.setOnClickListener(this);

        tvTitulCatTask = itemView.findViewById(R.id.tv_categoriataskMain);
        cantTask = itemView.findViewById(R.id.tv_Quanty);
        imgCategoriaTask = itemView.findViewById(R.id.img_categoriataskMain);
    }

    @Override
    public void onClick(View view) {
        if (mTapListener == null) return;
        mTapListener.onItemTap(view, getAdapterPosition());
    }
}
