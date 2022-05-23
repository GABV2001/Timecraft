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

public class AddReminderCasaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText tilRmCasaTitle, tilRmCasaDescription;

    int day, month, year;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder_casa);
        setup();
        }

    private void setup() {

        ImageButton showDatePicker = findViewById(R.id.showreminderdatepickerCasa_button);
        showDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDatePicker customDatePicker = new CustomDatePicker();
                customDatePicker.show(getSupportFragmentManager(), "PICK DATES");
            }
        });

        ImageButton showTimePicker = findViewById(R.id.showremindertimepickerReminderCasa);
        showTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTimePicker customTimePicker = new CustomTimePicker();
                customTimePicker.show(getSupportFragmentManager(), "PICK TIMES");
            }
        });


         findViewById(R.id.guardarReminderCasa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToSQLite();
                finish();
            }
        });

        tilRmCasaTitle = findViewById(R.id.remindeCasatitle);
        tilRmCasaDescription = findViewById(R.id.reminderCasaDescripcion);
        }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //Esta funcion es llamada para poder set la fecha al usuario

        String date = (dayOfMonth) + "/" + (month + 1) + "/" + (year);

        // Hacemos set a una al editext la fecha
        EditText dateView = findViewById(R.id.reminderDateview);

        // Luego tomamos los valores
        dateView.setText(date);

        // Also save the date to global variables so we can use it later
        this.day = dayOfMonth;
        this.month = month + 1;
        this.year = year;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {



        String time = (hourOfDay) + ":" + (minute);

        EditText timeView = findViewById(R.id.reminderCasatimeview);
        timeView.setText(time);


        this.hour = hourOfDay;
        this.minute = minute;
    }

    private void saveToSQLite() {

        String rmCasaTitle = tilRmCasaTitle.getText().toString();
        String rmCasaDescription = tilRmCasaDescription.getText().toString();



        SQLiteDatabase database = openOrCreateDatabase("reminderCasa", MODE_PRIVATE,null);



        String createTableQuery = "CREATE TABLE IF NOT EXISTS reminderCasa(idRecordatorio VARCHAR,titleRmCasa VARCHAR, descriptionRmcasa VARCHAR,day INT, month INT, year INT, hour INT, minute INT);";
        database.execSQL(createTableQuery);


        ContentValues values = new ContentValues();
        values.put("idRecordatorio", generadorId());
        values.put("titleRmcasa", rmCasaTitle);
        values.put(" descriptionRmcasa", rmCasaDescription);
        values.put("day", this.day);
        values.put("month", this.month);
        values.put("year", this.year);
        values.put("hour", this.hour);
        values.put("minute", this.minute);


        database.insert("reminderCasa", null, values);
      }

    public String generadorId(){
        return UUID.randomUUID().toString();
    }


}