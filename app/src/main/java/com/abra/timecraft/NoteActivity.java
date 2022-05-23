package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.abra.timecraft.Datetime.CustomDatePicker;
import com.abra.timecraft.adaptersViewHolderHelper.NoteAdapter;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.NoteModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;

public class NoteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, ItemTapListener {


    private static int posicion;

    private static final String TAG = NoteActivity.class.getName();
    RecyclerView.Adapter adapters;
    ArrayList<NoteModel> ntm = new ArrayList<>();

    int positions = -1 ;

    public static int getPosicion() {
        return posicion;
    }

    public static void setPosicion(int posicion) {
        NoteActivity.posicion = posicion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        findViewById(R.id.fab_addNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });

        MaterialToolbar toolbar = findViewById(R.id.topAppBaNote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notas");
        setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cldelete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.rmCalendar) {
            CustomDatePicker customDatePicker = new CustomDatePicker();
            customDatePicker.show(getSupportFragmentManager(), "PICK DATES");
        }
        if (id == R.id.rmDelete) {
            if(positions == -1){
                Toast.makeText(this, "No ha seleccionado ningún Recordatorio", Toast.LENGTH_SHORT).show();
            }else{
                getSelectedItem(positions);
            }
        }
        if (id == R.id.rmEdit) {
            if(positions == -1){
                Toast.makeText(this, "No ha seleccionado ningún Recordatorio  para poder editar", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent();
                setPosicion(positions);
                intent = new Intent(this, UpgradeNoteActivity.class);
                startActivity(intent);
            }

        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setup();
        loadData();
    }

    private void loadData() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        getFromSQLiteDatabase(day, month, year);
    }


    private void setup() {
        RecyclerView rvPoints = findViewById(R.id.rv_Note);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapters = new NoteAdapter(ntm, this);
        rvPoints.setAdapter(adapters);
        rvPoints.setLayoutManager(layoutManager);

    }

        private void getFromSQLiteDatabase(int day, int month, int year) {
            SQLiteDatabase database = openOrCreateDatabase("note", MODE_PRIVATE, null);

            String createTableQuery = "CREATE TABLE IF NOT EXISTS note(idNota VARCHAR,titleNote VARCHAR, descriptionNote VARCHAR, day INT, month INT, year INT, hour INT, minute INT);";
            database.execSQL(createTableQuery);
            database.execSQL(createTableQuery);

            String fetchDataQuery = "SELECT * from note WHERE day=" + day + " AND month=" + month + " AND year=" + year + ";";
            Cursor cursor = database.rawQuery(fetchDataQuery, null);

            cursor.moveToFirst();
            ntm.clear();

            if (cursor.getCount() == 0) {
                return;
            }

            do {
                NoteModel ntmM = new NoteModel(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7)
                        );
                ntm.add(ntmM);

            } while (cursor.moveToNext());
        }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        getFromSQLiteDatabase(dayOfMonth, month + 1, year);
        adapters.notifyDataSetChanged();
    }

    @Override
    public void onItemTap(View view, final int position) {
        positions = position;

}


    private void getSelectedItem(int position) {
        if (ntm == null) {
            Log.e(TAG, "invalid mModelList");
            return;
        }
        if (position > ntm.size()) {
            Log.e(TAG, "invalid position");
            return;
        }

        NoteModel selectedItemModel = ntm.get(position);
        DeleteSQLiteDatabase(selectedItemModel);
        setup();
        loadData();

    }

    private void DeleteSQLiteDatabase(NoteModel ntMode) {
        SQLiteDatabase database = openOrCreateDatabase("note", MODE_PRIVATE, null);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS note(idNota VARCHAR,titleNote VARCHAR, descriptionNote VARCHAR, year INT, month INT, day INT,hour INT, minute INT);";
        database.execSQL(createTableQuery);
        database.execSQL(createTableQuery);
        String[] parametro ={ntMode.getId()};
        String fetchDataQuery = "DELETE FROM note  WHERE  idNota =?";
        Cursor cursor = database.rawQuery(fetchDataQuery, parametro);
        cursor.moveToFirst();
    }
}