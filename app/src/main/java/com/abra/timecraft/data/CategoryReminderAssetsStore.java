package com.abra.timecraft.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.abra.timecraft.helpers.FileHelper;
import com.abra.timecraft.models.CategoryReminderModel;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryReminderAssetsStore implements ICategoryReminderSource {
    private final Gson parser;
    private final Context mContext;
    private static final String CATEGORIAREMINDER_FILE_NAME = "categoriareminder.json";

    public CategoryReminderAssetsStore(@NonNull Context mContext) {
        this.mContext = mContext;
        parser = new Gson();
    }

    @Override
    public List<CategoryReminderModel> getAll(int count) {
        String json = FileHelper.getJsonFromAssets(mContext, CATEGORIAREMINDER_FILE_NAME);
        CategoryReminderAssetsStore.ListResult listResult = parser.fromJson(json, CategoryReminderAssetsStore.ListResult.class);
        if (listResult == null) return null;
        return filterByCount(listResult.list, count);
    }

        private List<CategoryReminderModel> filterByCount(@NonNull List<CategoryReminderModel> originalList, int count) {
            if(count < 0) throw new IllegalArgumentException("Parametro count inválido");
            if(count == 0) return originalList;
            if(count >= originalList.size()) return originalList;
            return originalList.subList(0, count);
        }

        static class ListResult {
            @SerializedName("categoriareminder")
            List<CategoryReminderModel> list;

            public ListResult(List<CategoryReminderModel> list) {
                this.list = list;
            }
        }

}
