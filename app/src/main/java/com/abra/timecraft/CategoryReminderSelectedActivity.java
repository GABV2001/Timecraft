package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.abra.timecraft.models.CategoryReminderModel;

public class CategoryReminderSelectedActivity extends AppCompatActivity {

    public static final String ARG_LIST1 = "categoriareminder_list";
    private static final String TAG = CategoryReminderSelectedActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_reminder_selected);
        setup();
    }


    public void setup() {
        Intent navIntent = getIntent();
        CategoryReminderModel selectedCategory = navIntent.getParcelableExtra(ARG_LIST1);
        if (selectedCategory == null) {
            Log.e(TAG, "Categoria Recordatorio seleccionada es null");
            return;
        }
        loadSelectedPoint(selectedCategory);
    }

    private void loadSelectedPoint(CategoryReminderModel categoriaReminder) {
        //Redireccionando a la catego
        Intent intent = null;
        String categoriaElegida = categoriaReminder.getTituloCatReminder();

        switch (categoriaElegida) {
            case "Personal":
                intent = new Intent(this, ReminderPersonalActivity.class);
                break;

            case "Casa":
                intent = new Intent(this, ReminderCasaActivity.class);
                break;

            case "Reunion":
                intent = new Intent(this, ReminderReunionActivity.class);
                break;
        }
        startActivity(intent);
        this.onBackPressed();
    }
}
