package com.abra.timecraft.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoriaMainModel implements Parcelable {
    private String id;
    private  String titulo;
    private String category;

    public CategoriaMainModel(String titulo, String category) {

        this.titulo = titulo;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    protected CategoriaMainModel(Parcel in) {
        titulo = in.readString();
        category = in.readString();

    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(category);

    }

    public static final Parcelable.Creator<CategoriaMainModel> CREATOR = new Parcelable.Creator<CategoriaMainModel>() {
        @Override
        public CategoriaMainModel createFromParcel(Parcel in) {
            return new CategoriaMainModel(in);
        }

        @Override
        public CategoriaMainModel[] newArray(int size) {
            return new CategoriaMainModel[size];
        }
    };
}
