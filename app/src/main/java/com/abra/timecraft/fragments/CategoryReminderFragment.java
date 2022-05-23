package com.abra.timecraft.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abra.timecraft.R;
import com.abra.timecraft.adaptersViewHolderHelper.CategoryReminderAdapter;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoryReminderModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryReminderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryReminderFragment extends Fragment {
    private static final String ARG_LISTS1 = "categoryReminder_list";
    private CategoryReminderAdapter catReminderAdapter;
    private ArrayList<CategoryReminderModel> mcatReminderList;
    private ItemTapListener itemTapListener;


    public CategoryReminderFragment() {

    }

    public static CategoryReminderFragment newInstance(ArrayList<CategoryReminderModel> cRem) {
        CategoryReminderFragment fragment = new CategoryReminderFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LISTS1, cRem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        mcatReminderList = getArguments().getParcelableArrayList(ARG_LISTS1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_reminder, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup(view);
    }

    private void setup(View view) {
        setupCategoryListView(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof ItemTapListener)) {
            throw new ClassCastException("Actividad padre no implementa ItemTapListener");
        }
        itemTapListener = (ItemTapListener) context;
    }

    private void setupCategoryListView(View view) {
        RecyclerView rvPoint = view.findViewById(R.id.rv_categoryReminder);
        catReminderAdapter = new CategoryReminderAdapter(mcatReminderList, itemTapListener);
        rvPoint.setAdapter(catReminderAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rvPoint.setLayoutManager(layoutManager);
        rvPoint.setHasFixedSize(true);

    }
}