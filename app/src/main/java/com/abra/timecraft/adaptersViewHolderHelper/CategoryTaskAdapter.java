package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoryTaskModel;

import java.util.List;

public class CategoryTaskAdapter extends RecyclerView.Adapter<CategoryTaskViewHolder> {


    @NonNull
    private List<CategoryTaskModel> mModelLists;
    @Nullable
    private final ItemTapListener mTapListener;

    public CategoryTaskAdapter(@NonNull List<CategoryTaskModel> mModelList, @Nullable ItemTapListener mTapListener) {
        this.mModelLists = mModelList;
        this.mTapListener = mTapListener;
    }

    @Override
    public CategoryTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_categorytask, parent, false);
        CategoryTaskViewHolder viewHolder = new CategoryTaskViewHolder(itemView, mTapListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryTaskViewHolder holder, int position) {
        CategoryTaskModel currentModel = mModelLists.get(position);

        holder.tvTitulCatTask.setText(currentModel.getTitutloCatTarea());
        holder.cantTask.setText(currentModel.getItemTarea());
        holder.imgCategoriaTask.setImageResource(
                CategoryTaskHelper.getResIdByCategoryTask(currentModel.getImgCatTarea())
        );
    }

    @Override
    public int getItemCount() {
        return mModelLists.size();
    }
}
