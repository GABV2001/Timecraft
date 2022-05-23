package com.abra.timecraft.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryReminderModel implements Parcelable {
    String tituloCatReminder;
    String imgCatReminder;

    public CategoryReminderModel(String tituloCatReminder, String imgCatReminder) {
        this.tituloCatReminder = tituloCatReminder;
        this.imgCatReminder = imgCatReminder;
    }

    public String getTituloCatReminder() {
        return tituloCatReminder;
    }

    public void setTituloCatReminder(String tituloCatReminder) {
        this.tituloCatReminder = tituloCatReminder;
    }

    public String getImgCatReminder() {
        return imgCatReminder;
    }

    public void setImgCatReminder(String imgCatReminder) {
        this.imgCatReminder = imgCatReminder;
    }

    protected CategoryReminderModel(Parcel in) {
        tituloCatReminder = in.readString();
        imgCatReminder = in.readString();
    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tituloCatReminder);
        dest.writeString(imgCatReminder);
    }

    public static final Parcelable.Creator<CategoryReminderModel> CREATOR = new Parcelable.Creator<CategoryReminderModel>() {
        @Override
        public CategoryReminderModel createFromParcel(Parcel in) {
            return new CategoryReminderModel(in);
        }

        @Override
        public CategoryReminderModel[] newArray(int size) {
            return new CategoryReminderModel[size];
        }
    };


}
