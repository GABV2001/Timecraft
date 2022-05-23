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
import com.abra.timecraft.adaptersViewHolderHelper.CategoryTaskAdapter;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoryTaskModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryTaskFragment extends Fragment {


    private static final String ARG_LISTS = "categorytaskamain_list";

    private CategoryTaskAdapter catTaskAdapter;
    private ArrayList<CategoryTaskModel> mcategorTaskList;
    private ItemTapListener itemTapListener;

    public CategoryTaskFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CategoryTaskFragment newInstance(ArrayList<CategoryTaskModel> ctask) {
        CategoryTaskFragment fragment = new CategoryTaskFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LISTS, ctask);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mcategorTaskList = getArguments().getParcelableArrayList(ARG_LISTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_category_task, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof ItemTapListener)) {
            throw new ClassCastException("Actividad padre no implementa ItemTapListener");
        }
        itemTapListener = (ItemTapListener) context;
    }

    private void setup(@NonNull View view) {
        setupPointListView(view);
    }

    private void setupPointListView(View view) {
        RecyclerView rvPoint = view.findViewById(R.id.rv_categoryTask);
        catTaskAdapter = new CategoryTaskAdapter(mcategorTaskList, itemTapListener);
        rvPoint.setAdapter(catTaskAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rvPoint.setLayoutManager(layoutManager);
        rvPoint.setHasFixedSize(true);
    }
}