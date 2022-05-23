package com.abra.timecraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.abra.timecraft.data.CategoryReminderRepository;
import com.abra.timecraft.fragments.CategoryReminderFragment;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoryReminderModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class CategoryReminderActivity extends AppCompatActivity implements ItemTapListener {

    private static final String TAG = CategoryTaskActivity.class.getName();
    private CategoryReminderRepository mCateReminder;
    private List<CategoryReminderModel> mModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_reminder);

      MaterialToolbar toolbar = findViewById(R.id.topAppBarRm);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recordatorio");

        setup();
    }


    private void setup() {
        mCateReminder = new CategoryReminderRepository(getBaseContext());
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
        if(mCateReminder == null) {
            Log.e(TAG, "mPointsRepository no debería ser null");
            return;
        }
        mModelList = mCateReminder.getAll();

        loadCategoryrReminderFragment(new ArrayList<CategoryReminderModel>(mModelList));
    }

    private void loadCategoryrReminderFragment(ArrayList<CategoryReminderModel> cRem) {
        FragmentTransaction frgTran = getSupportFragmentManager().beginTransaction();
        frgTran.replace(R.id.categoriaReminder, CategoryReminderFragment.newInstance(cRem));
        frgTran.commit();
    }


    public void onItemTap(View view, int position) {
        showMessageWithSelectedItem(position);
    }

    private void showMessageWithSelectedItem(int position) {
        if(mModelList == null) {
            Log.e(TAG, "invalid mModelList");
            return;
        }
        if(position > mModelList.size()) {
            Log.e(TAG, "invalid position");
            return;
        }

        CategoryReminderModel selectedItemModel = mModelList.get(position);
        navigateToProfile(selectedItemModel);
        //showMessageCategory(selectedItemModel);
    }

    private void navigateToProfile(CategoryReminderModel categoria) {
        launchSelectedCategoryReminder(categoria);
    }

    private void launchSelectedCategoryReminder(CategoryReminderModel selectedMain) {
        Intent intent = new Intent(this, CategoryReminderSelectedActivity.class);
        intent.putExtra(CategoryReminderSelectedActivity.ARG_LIST1, selectedMain);
        startActivity(intent);
    }
}