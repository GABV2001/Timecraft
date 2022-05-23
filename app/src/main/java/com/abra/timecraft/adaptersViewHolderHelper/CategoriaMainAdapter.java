package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoriaMainModel;

import java.util.List;

public class CategoriaMainAdapter extends RecyclerView.Adapter<CategoriaMainViewHolder> {

    @NonNull
    private List<CategoriaMainModel> mModelList;
    @Nullable
    private final ItemTapListener mTapListener;

    public CategoriaMainAdapter(@NonNull List<CategoriaMainModel> modelist, @Nullable ItemTapListener mTapListener) {
        this.mModelList = modelist;
        this.mTapListener = mTapListener;
    }

    public void updateList(List<CategoriaMainModel> newList) {
        mModelList = newList;
     //   notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CategoriaMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_categoriam, parent, false);
        CategoriaMainViewHolder viewHolder = new CategoriaMainViewHolder(itemView, mTapListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaMainViewHolder holder, int position) {
        CategoriaMainModel currentModel = mModelList.get(position);
        holder.tvTitu.setText( currentModel.getTitulo());
        holder.imgCategoriaMain.setImageResource(
                CategoriaMainHelper.getResIdByCategory(currentModel.getCategory())
        );
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }
}
