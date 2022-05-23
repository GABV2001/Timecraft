package com.abra.timecraft.data;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import com.abra.timecraft.helpers.FileHelper;
import com.abra.timecraft.models.CategoriaMainModel;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class CategoryMainAssetsStore implements ICategoriaSource{
    private final Gson parser;
    private final Context mContext;
    private static final String CATEGORIAMAIN_FILE_NAME = "categoriamain.json";

    public CategoryMainAssetsStore(@NonNull Context mContext) {
        this.mContext = mContext;
        parser = new Gson();
    }

    public List<CategoriaMainModel> getAll(int count) {
        String json = FileHelper.getJsonFromAssets(mContext, CATEGORIAMAIN_FILE_NAME);
        ListResult listResult = parser.fromJson(json, ListResult.class);
        if(listResult == null) return null;
        return filterByCount(listResult.list, count);
    }

    private List<CategoriaMainModel> filterByCount(@NonNull List<CategoriaMainModel> originalList, int count) {
        if(count < 0) throw new IllegalArgumentException("Parametro count inválido");
        if(count == 0) return originalList;
        if(count >= originalList.size()) return originalList;
        return originalList.subList(0, count);
    }

    static class ListResult {
        @SerializedName("data")
        List<CategoriaMainModel> list;

        public ListResult(List<CategoriaMainModel> list) {
            this.list = list;
        }
    }
}
