package com.abra.timecraft.adaptersViewHolderHelper;

import androidx.annotation.DrawableRes;

import com.abra.timecraft.R;
import com.abra.timecraft.data.ICategoryReminderSource;

public class CategoryReminderHelper {
    @DrawableRes
    public static int getResIdByCategoryReminder(String categoryReminder) {
        switch(categoryReminder) {
            case ICategoryReminderSource.Categories.Casa:
                return R.drawable.imgcasa;
            case ICategoryReminderSource.Categories.Tiempolibre:
                return R.drawable.imgtiempolibre;
            case ICategoryReminderSource.Categories.Reunion:
                return R.drawable.imgreunion;
            default:
                return R.drawable.imgpersonal;
        }
    }
}
