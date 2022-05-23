package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.abra.timecraft.models.NoteModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class UpgradeNoteActivity extends AppCompatActivity  {


    ArrayList<NoteModel> ntmUp = new ArrayList<>();

    EditText tiltitleNoteUp,tilDescriptionNoteup; 
    Button UpgradeNote;

    String cargaNtmTitle = null;
    String carggaNtmDescription = null;
    String cargarNtId = null;

    int day, month, year;
    int hour, minute;

    NoteModel nt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_note);
        setup();
    }

    private void setup() {

        tiltitleNoteUp = findViewById(R.id.NotatitleUpgrade);
        tilDescriptionNoteup = findViewById(R.id.NotaDescripcionUpgrade);
        
        getFromSQLiteDatabase();
        obtenerPositionitem();

        UpgradeNote = findViewById(R.id.ActualizarNotaUpgrade);
        UpgradeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    UpgradeDataSQlite();
                    finish();
            }
        });
    }

    private void getFromSQLiteDatabase() {
        SQLiteDatabase database = openOrCreateDatabase("note", MODE_PRIVATE, null);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS note(idNota VARCHAR,titleNote VARCHAR, descriptionNote VARCHAR, year INT, month INT, day INT,hour INT, minute INT);";
        database.execSQL(createTableQuery);
        database.execSQL(createTableQuery);

        String fetchDataQuery = "SELECT * from note;";
        Cursor cursor = database.rawQuery(fetchDataQuery, null);

        cursor.moveToFirst();
        ntmUp.clear();

        if (cursor.getCount() == 0) {
            return;
        }

        do {
            NoteModel ntmU = new NoteModel(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7)
            );
            ntmUp.add(ntmU);
        } while (cursor.moveToNext());
    }

    public void  UpgradeDataSQlite(){
            Calendar c = Calendar.getInstance();
            this.day = c.get(Calendar.DAY_OF_MONTH);
            this.month = c.get(Calendar.MONTH) + 1;
            this.year = c.get(Calendar.YEAR);
            this.hour = c.get(Calendar.HOUR);
            this.minute = c.get(Calendar.MINUTE);

            String TitlenoteU = tiltitleNoteUp.getText().toString();
            String DescriptionU = tilDescriptionNoteup.getText().toString();


            SQLiteDatabase database = openOrCreateDatabase("note", MODE_PRIVATE,null);
            String createTableQuery = "CREATE TABLE IF NOT EXISTS note(idNota VARCHAR,titleNote VARCHAR, descriptionNote VARCHAR, day INT, month INT, year INT, hour INT, minute INT);";
            database.execSQL(createTableQuery);
            database.execSQL(createTableQuery);

        ContentValues values = new ContentValues();
             String[] parametro ={cargarNtId};
            values.put("titleNote", TitlenoteU);
            values.put(" descriptionNote", DescriptionU);
            values.put("day", this.day);
            values.put("month", this.month);
            values.put("year", this.year);
            database.update("note", values, "idNota =?", parametro);
        }



    public void obtenerPositionitem(){

        int position = NoteActivity.getPosicion();

        for ( NoteModel ntm: ntmUp) {
            if(position == position){
                cargarNtId = ntm.getId();
                cargaNtmTitle =  ntm.getTitle();
               carggaNtmDescription =  ntm.getDescription();
            }
        }
        cargarDatos();
    }
      public void cargarDatos(){
        tiltitleNoteUp .setText(cargaNtmTitle);
       tilDescriptionNoteup.setText(carggaNtmDescription);
      }
}