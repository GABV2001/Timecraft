package com.abra.timecraft.data;

import android.content.Context;

import com.abra.timecraft.models.CategoriaMainModel;

import java.util.List;

public class CategoyMainRepository {
    private final ICategoriaSource mSource;

   public CategoyMainRepository(Context context) { mSource = new CategoryMainAssetsStore(context);  }

//   public PointsRepository( ) { mSource = new PointsMockSource();  }

    public CategoyMainRepository(ICategoriaSource source) { mSource = source;  }

    public List<CategoriaMainModel> getAll() {return mSource.getAll(50); }
}
