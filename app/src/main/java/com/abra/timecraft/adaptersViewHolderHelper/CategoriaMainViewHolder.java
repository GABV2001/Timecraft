package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;

public class CategoriaMainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvTitu;
    public ImageView imgCategoriaMain;
    @Nullable
    private final ItemTapListener mTapListener;

    public CategoriaMainViewHolder(@NonNull View itemView, @Nullable ItemTapListener tapListener) {
        super(itemView);
        mTapListener = tapListener;
        itemView.setOnClickListener(this);


        tvTitu = itemView.findViewById(R.id.tv_categoriaMain);
        imgCategoriaMain = itemView.findViewById(R.id.img_categoriaMain);
    }

    @Override
    public void onClick(View view) {
        if (mTapListener == null) return;
        mTapListener.onItemTap(view, getAdapterPosition());
    }
}