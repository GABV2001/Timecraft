package com.abra.timecraft;

import android.content.Intent;
import android.os.Bundle;

import com.abra.timecraft.models.CategoriaMainModel;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

public class CategorySelectedActivity extends AppCompatActivity {

    public static final String ARG_LIST = "categoriamain_list";
    private static final String TAG = CategorySelectedActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    public void setup() {
        Intent navIntent = getIntent();
        CategoriaMainModel selectedCategory = navIntent.getParcelableExtra(ARG_LIST);
        if(selectedCategory == null) {
            Log.e(TAG, "Categoria seleccionada es null");
            return;
        }
        loadSelectedPoint(selectedCategory);
    }
    private void loadSelectedPoint(CategoriaMainModel categoria) {
      //Redireccionando a la catego
        Intent intent = null;
        String categoriaElegida = categoria.getTitulo();

        switch (categoriaElegida){
            case "Recordatorios":
                 intent = new Intent(this, CategoryReminderActivity.class);
                 break;

            case "Notas":
                intent = new Intent(this, NoteActivity.class);
                 break;

            case "Tareas" :
                intent = new Intent(this, CategoryTaskActivity.class);
                    break;

            case "Evento" :
                intent = new Intent(this, SignUpActivity.class);
                break;
        }
        startActivity(intent);
        this.onBackPressed();
    }



}