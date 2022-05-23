package com.abra.timecraft.data;

import com.abra.timecraft.models.CategoryTaskModel;

import java.util.List;

public interface ICategoryTaskSource {
    interface Categories {
        String Trabajo  = "trabajo";
        String Personal   = "personal";
        String Estudio  = "estudio";
    }

    List<CategoryTaskModel> getAll(int count);
}
