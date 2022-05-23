package com.abra.timecraft.adaptersViewHolderHelper;

import androidx.annotation.DrawableRes;

import com.abra.timecraft.R;
import com.abra.timecraft.data.ICategoryTaskSource;

public class CategoryTaskHelper {

    @DrawableRes
    public static int getResIdByCategoryTask(String categoryTask) {
        switch(categoryTask) {
            case ICategoryTaskSource.Categories.Estudio:
                return R.drawable.imgestudio;
            case ICategoryTaskSource.Categories.Personal:
                return R.drawable.imgpersonal;
            default:
                return R.drawable.imgtrabajo;
        }
    }
}
