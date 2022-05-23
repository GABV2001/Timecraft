package com.abra.timecraft.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.abra.timecraft.helpers.FileHelper;
import com.abra.timecraft.models.CategoryTaskModel;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryTaskAssetsStore implements ICategoryTaskSource {
    private final Gson parser;
    private final Context mContext;
    private static final String CATEGORIATASK_FILE_NAME = "categoriatask.json";

    public CategoryTaskAssetsStore(@NonNull Context mContext) {
        this.mContext = mContext;
        parser = new Gson();
    }


    @Override
    public List<CategoryTaskModel> getAll(int count) {
        String json = FileHelper.getJsonFromAssets(mContext, CATEGORIATASK_FILE_NAME);
        CategoryTaskAssetsStore.ListResult listResult = parser.fromJson(json, CategoryTaskAssetsStore.ListResult.class);
        if(listResult == null) return null;
        return filterByCount(listResult.list, count);
    }

    private List<CategoryTaskModel> filterByCount(@NonNull List<CategoryTaskModel> originalList, int count) {
        if(count < 0) throw new IllegalArgumentException("Parametro count inválido");
        if(count == 0) return originalList;
        if(count >= originalList.size()) return originalList;
        return originalList.subList(0, count);
    }

    static class ListResult {
        @SerializedName("categoriat")
        List<CategoryTaskModel> list;

        public ListResult(List<CategoryTaskModel> list) {
            this.list = list;
        }
    }
}
