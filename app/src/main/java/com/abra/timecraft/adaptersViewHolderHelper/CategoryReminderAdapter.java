package com.abra.timecraft.adaptersViewHolderHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abra.timecraft.R;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoryReminderModel;

import java.util.List;

public class CategoryReminderAdapter extends RecyclerView.Adapter<CategoryReminderViemHolder> {

    @NonNull
    private List<CategoryReminderModel> mModelLists1;
    @Nullable
    private final ItemTapListener mTapListener;

    public CategoryReminderAdapter(@NonNull List<CategoryReminderModel> mModelLists1, @Nullable ItemTapListener mTapListener) {
        this.mModelLists1 = mModelLists1;
        this.mTapListener = mTapListener;
    }


    @NonNull
    @Override
    public CategoryReminderViemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_categoryreminder, parent, false);
        CategoryReminderViemHolder viewHolder = new CategoryReminderViemHolder(itemView, mTapListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryReminderViemHolder holder, int position) {
        CategoryReminderModel currentmodel = mModelLists1.get(position);

        holder.tvTituloCatReminder.setText(currentmodel.getTituloCatReminder());
      holder.imgCategoriaReminder.setImageResource(CategoryReminderHelper.getResIdByCategoryReminder(currentmodel.getImgCatReminder())
      );
    }

    @Override
    public int getItemCount() {
        return mModelLists1.size();
    }
}
