package com.abra.timecraft.Datetime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class CustomDatePicker extends DialogFragment {



    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Context context = getActivity();

        DatePickerDialog.OnDateSetListener onDateSetListener = (DatePickerDialog.OnDateSetListener) getActivity();

        return new DatePickerDialog(context, onDateSetListener, year, month, dayOfMonth);


    }
}
