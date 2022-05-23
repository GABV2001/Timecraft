package com.abra.timecraft.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryTaskModel implements Parcelable {
    String titutloCatTarea;
    String imgCatTarea;
    String itemTarea;

    public CategoryTaskModel(String titutloCatTarea, String imgCatTarea, String itemTareaa) {
        this.titutloCatTarea = titutloCatTarea;
        this.imgCatTarea = imgCatTarea;
        this.itemTarea = itemTareaa;
    }

    public String getTitutloCatTarea() {
        return titutloCatTarea;
    }

    public void setTitutloCatTarea(String titutloCatTarea) {
        this.titutloCatTarea = titutloCatTarea;
    }

    public String getImgCatTarea() {
        return imgCatTarea;
    }

    public void setImgCatTarea(String imgCatTarea) {
        this.imgCatTarea = imgCatTarea;
    }

    public String getItemTarea() {
        return itemTarea;
    }

    public void setItemTarea(String itemTareaa) {
        this.itemTarea = itemTareaa;
    }


    protected CategoryTaskModel(Parcel in) {
        titutloCatTarea = in.readString();
        imgCatTarea = in.readString();
        itemTarea = in.readString();
    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titutloCatTarea);
        dest.writeString(imgCatTarea);
        dest.writeString(itemTarea);

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
