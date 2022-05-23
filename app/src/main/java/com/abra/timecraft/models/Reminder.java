package com.abra.timecraft.models;

public class Reminder {
    private  String  idRecordatorio;
    private String titutloReco;
    private String descripcion;
    public int day, month, year;
    public  int hour;
    String minute;

        public Reminder(String idRecordatorio, String titutloReco, String descripcion, int day, int month, int year, int hour, int minute) {
        this.idRecordatorio = idRecordatorio;
        this.titutloReco = titutloReco;
        this.descripcion = descripcion;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        if (minute < 10) {
            this.minute = "0" + minute;
        } else {
            this.minute = String.valueOf(minute);
        }
    }


    public String getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(String idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getTitutloReco() {
        return titutloReco;
    }

    public void setTitutloReco(String titutloReco) {
        this.titutloReco = titutloReco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

}

