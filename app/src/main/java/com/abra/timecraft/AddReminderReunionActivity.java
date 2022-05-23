package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.abra.timecraft.Datetime.CustomDatePicker;
import com.abra.timecraft.Datetime.CustomTimePicker;

import java.util.UUID;

public class AddReminderReunionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private EditText tilRmReunionTitle, tilRmReunionDescription;

    int day, month, year;
    int hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder_reunion);
        setup();
    }

    private void setup() {
        ImageButton showDatePicker = findViewById(R.id.showreminderdatepickerReunion_button);
        showDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDatePicker customDatePicker = new CustomDatePicker();
                customDatePicker.show(getSupportFragmentManager(), "PICK DATE");
            }
        });

        ImageButton showTimePicker = findViewById(R.id.showremindertimepickerReminderReunion);
        showTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTimePicker customTimePicker = new CustomTimePicker();
                customTimePicker.show(getSupportFragmentManager(), "PICK TIME");
            }
        });

        findViewById(R.id.guardarReminderReunion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveToSQLite();


                finish();
            }
        });

        tilRmReunionTitle = findViewById(R.id.remindeReuniontitle);
        tilRmReunionDescription = findViewById(R.id.reminderReunionDescripcion);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {



        String date = (dayOfMonth) + "/" + (month + 1) + "/" + (year);


        EditText dateViews = findViewById(R.id.reminderDateviewReunion);


        dateViews.setText(date);


        this.day = dayOfMonth;
        this.month = month + 1;
        this.year = year;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String time = (hourOfDay) + ":" + (minute);

        EditText timeViews = findViewById(R.id.reminderReuniontimeview);
        timeViews.setText(time);


        this.hour = hourOfDay;
        this.minute = minute;
    }

    private void saveToSQLite() {

        String rmCasaTitle = tilRmReunionTitle.getText().toString();
        String rmCasaDescription = tilRmReunionDescription.getText().toString();



        SQLiteDatabase database = openOrCreateDatabase("reminderReunion", MODE_PRIVATE,null);



        String createTableQuery = "CREATE TABLE IF NOT EXISTS reminderReunion(idReunion VARCHAR, titleRmReunion VARCHAR, descriptionRmReunion VARCHAR,day INT, month INT, year INT, hour INT, minute INT);";
        database.execSQL(createTableQuery);


        ContentValues values = new ContentValues();
        values.put("idReunion", generadorId());
        values.put("titleRmReunion ", rmCasaTitle);
        values.put(" descriptionRmReunion", rmCasaDescription);
        values.put("day", this.day);
        values.put("month", this.month);
        values.put("year", this.year);
        values.put("hour", this.hour);
        values.put("minute", this.minute);


        database.insert("reminderReunion", null, values);
    }
    public String generadorId(){
        return UUID.randomUUID().toString();
    }


}