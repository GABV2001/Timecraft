package com.abra.timecraft.adaptersViewHolderHelper;

import androidx.annotation.DrawableRes;

import com.abra.timecraft.R;
import com.abra.timecraft.data.ICategoriaSource;

public class CategoriaMainHelper {

    @DrawableRes
    public static int getResIdByCategory(String category) {
        switch(category) {
            case ICategoriaSource.Categories.Evento:
                return R.drawable.imgevento;
            case ICategoriaSource.Categories.Nota:
                return R.drawable.imgnota;
            case ICategoriaSource.Categories.Recordatorio:
                return R.drawable.imgrecordatorio;
            default:
                return R.drawable.imgtarea;
        }
    }
}
