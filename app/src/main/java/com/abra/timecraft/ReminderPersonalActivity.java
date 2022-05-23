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
import com.abra.timecraft.adaptersViewHolderHelper.ReminderPersonalAdapter;
import com.abra.timecraft.adaptersViewHolderHelper.ReminderReunionAdapter;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.Reminder;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;

public class ReminderPersonalActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, ItemTapListener {

    private static final String TAG = ReminderPersonalActivity.class.getName();

    RecyclerView.Adapter adapters;

    ArrayList<Reminder> rmPersonal = new ArrayList<>();

    int positions = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_personal);

        findViewById(R.id.fab_addReminderPersonal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddReminderPersonalActivity.class);
                startActivity(intent);
            }
        });

        MaterialToolbar toolbar = findViewById(R.id.topAppBarRmPersonal);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Personal");
        setup();
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        setup();
    }

    private void loadData() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        getFromSQLiteDatabase(day, month, year);
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
            }else{    getSelectedItem(positions);
            }
        }
        return true;
    }


    private void setup() {
        RecyclerView rvReminderPersonal = findViewById(R.id.rv_reminderPersonal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapters = new ReminderPersonalAdapter(rmPersonal, this);
        rvReminderPersonal.setAdapter(adapters);
        rvReminderPersonal.setLayoutManager(layoutManager);
    }

    private void getFromSQLiteDatabase(int day, int month, int year) {
        SQLiteDatabase database = openOrCreateDatabase("reminderPersonal", MODE_PRIVATE, null);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS reminderPersonal(idPersonal VARCHAR, titleRmPersonal VARCHAR, descriptionRmPersonal VARCHAR,day INT, month INT, year INT, hour INT, minute INT);";
        database.execSQL(createTableQuery);
        database.execSQL(createTableQuery);

        String fetchDataQuery = "SELECT * from reminderPersonal WHERE day=" + day + " AND month=" + month + " AND year=" + year + ";";
        Cursor cursor = database.rawQuery(fetchDataQuery, null);

        cursor.moveToFirst();

        rmPersonal.clear();


        if (cursor.getCount() == 0) {
            return;
        }

        do {
            Reminder rmPersonals = new Reminder(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7));

            rmPersonal.add(rmPersonals);

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
        if (rmPersonal == null) {
            Log.e(TAG, "invalid mModelList");
            return;
        }
        if (position > rmPersonal.size()) {
            Log.e(TAG, "invalid position");
            return;
        }
        Reminder selectedItemModel = rmPersonal.get(position);
        DeleteSQLiteDatabase(selectedItemModel);
        setup();
        loadData();
    }

    private void DeleteSQLiteDatabase(Reminder rmPersonalModel) {
        SQLiteDatabase database = openOrCreateDatabase("reminderPersonal", MODE_PRIVATE, null);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS reminderPersonal(idPersonal VARCHAR, titleRmPersonal VARCHAR, descriptionRmPersonal VARCHAR,day INT, month INT, year INT, hour INT, minute INT);";
        database.execSQL(createTableQuery);
        database.execSQL(createTableQuery);
        String[] parametro ={rmPersonalModel.getIdRecordatorio()};
        String fetchDataQuery = "DELETE FROM reminderPersonal  WHERE  idPersonal =?";
        Cursor cursor = database.rawQuery(fetchDataQuery, parametro);
        cursor.moveToFirst();
    }

}