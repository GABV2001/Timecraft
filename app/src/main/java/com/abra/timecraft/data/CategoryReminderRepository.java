package com.abra.timecraft.data;

import android.content.Context;

import com.abra.timecraft.models.CategoryReminderModel;

import java.util.List;

public class CategoryReminderRepository {
    ICategoryReminderSource mSource;

    public CategoryReminderRepository(Context context) { mSource = new CategoryReminderAssetsStore(context);  }

    public CategoryReminderRepository(ICategoryReminderSource source) { mSource = source;  }

    public List<CategoryReminderModel> getAll() {return mSource.getAll(50); }
}
