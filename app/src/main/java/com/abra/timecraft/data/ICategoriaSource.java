package com.abra.timecraft.data;

import com.abra.timecraft.models.CategoriaMainModel;

import java.util.List;

public interface ICategoriaSource {
    interface Categories {
        String Recordatorio  = "recordatorio";
        String Evento   = "evento";
        String Nota  = "nota";
        String Tarea  = "tarea";
    }

    List<CategoriaMainModel> getAll(int count);
}
