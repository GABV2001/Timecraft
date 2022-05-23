package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.UUID;

public class AddNoteActivity extends AppCompatActivity {

    private EditText tilNoteTitle, tilNoteDescription;

    int day, month, year;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        setup();
    }

    private void setup() {

        findViewById(R.id.guardarNota).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToSQLite();
                finish();
            }
        });

        tilNoteTitle = findViewById(R.id.Notatitle);
        tilNoteDescription = findViewById(R.id.NotaDescripcion);
    }

    private void saveToSQLite() {
        Calendar c = Calendar.getInstance();
       this.day = c.get(Calendar.DAY_OF_MONTH);
        this.month = c.get(Calendar.MONTH) + 1;
        this.year = c.get(Calendar.YEAR);
        this.hour = c.get(Calendar.HOUR);
        this.minute = c.get(Calendar.MINUTE);


        String Titlenote = tilNoteTitle.getText().toString();
        String Description = tilNoteDescription.getText().toString();


        SQLiteDatabase database = openOrCreateDatabase("note", MODE_PRIVATE,null);


        String createTableQuery = "CREATE TABLE IF NOT EXISTS note(idNota VARCHAR,titleNote VARCHAR, descriptionNote VARCHAR, day INT, month INT, year INT, hour INT, minute INT);";
        database.execSQL(createTableQuery);

        ContentValues values = new ContentValues();
        values.put("idNota", generadorId());
        values.put("titleNote", Titlenote);
        values.put(" descriptionNote", Description);
        values.put("day", this.day);
        values.put("month", this.month);
        values.put("year", this.year);
        values.put("hour", this.hour);
        values.put("minute", this.minute);

        database.insert("note", null, values);
    }

    public String generadorId(){
        return UUID.randomUUID().toString();
    }

}