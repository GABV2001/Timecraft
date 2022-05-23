package com.abra.timecraft.data;

import android.content.Context;

import com.abra.timecraft.models.CategoryTaskModel;

import java.util.List;

public class CategoryTaskRepository {

    ICategoryTaskSource mSource;

    public CategoryTaskRepository(Context context) { mSource = new CategoryTaskAssetsStore(context);  }

    public CategoryTaskRepository(ICategoryTaskSource source) { mSource = source;  }

    public List<CategoryTaskModel> getAll() {return mSource.getAll(50); }
}
