package com.abra.timecraft;

import android.os.Bundle;

import com.abra.timecraft.data.CategoryTaskRepository;
import com.abra.timecraft.fragments.CategoryTaskFragment;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoryTaskModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CategoryTaskActivity extends AppCompatActivity implements ItemTapListener {

    private static final String TAG = CategoryTaskActivity.class.getName();
    private CategoryTaskRepository mCateTaskRepo;
    private List<CategoryTaskModel> mModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_task);
        setup();
       }

    public void setup() {
        mCateTaskRepo = new CategoryTaskRepository(getBaseContext());
        mModelList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        if(!mModelList.isEmpty()) {
            Log.d(TAG, "Ya existen valores en la lista");

            return;
        }
        if(mCateTaskRepo == null) {
            Log.e(TAG, "mPointsRepository no debería ser null");
            return;
        }
        mModelList = mCateTaskRepo.getAll();

        loadCategoryTaskFragment(new ArrayList<CategoryTaskModel>(mModelList));
    }

    private void loadCategoryTaskFragment(ArrayList<CategoryTaskModel> cTask) {
        FragmentTransaction frgTran = getSupportFragmentManager().beginTransaction();
        frgTran.replace(R.id.categoriaTask, CategoryTaskFragment.newInstance(cTask));
        frgTran.commit();
    }

    @Override
    public void onItemTap(View view, int poistion) {
    }
}