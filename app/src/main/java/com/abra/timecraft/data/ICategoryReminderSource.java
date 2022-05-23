package com.abra.timecraft.data;

import com.abra.timecraft.models.CategoryReminderModel;

import java.util.List;

public interface ICategoryReminderSource {

        interface Categories {
            String Trabajo  = "trabajo";
            String Casa  = "casa";
            String Tiempolibre  = "tiempolibre";
            String Reunion = "reunion";

        }

        List<CategoryReminderModel> getAll(int count);
}
