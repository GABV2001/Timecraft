package com.abra.timecraft.Datetime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class CustomTimePicker extends DialogFragment {

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        Context context = getActivity();


        TimePickerDialog.OnTimeSetListener onTimeSetListener = (TimePickerDialog.OnTimeSetListener) getActivity();


        return new TimePickerDialog(context, onTimeSetListener, hour, minute, false);
    }}
