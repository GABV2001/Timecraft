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
import com.abra.timecraft.adaptersViewHolderHelper.CategoriaMainAdapter;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.CategoriaMainModel;

import java.util.ArrayList;

public class CategoriaMainFragment extends Fragment {

    private static final String ARG_LIST = "categoriamain_list";

    private CategoriaMainAdapter categmainAdapter;
    private ArrayList<CategoriaMainModel> mcategoriasList;
    private ItemTapListener itemTapListener;

    public CategoriaMainFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CategoriaMainFragment newInstance(ArrayList<CategoriaMainModel> cmain) {
        CategoriaMainFragment fragment = new CategoriaMainFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST, cmain);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mcategoriasList = getArguments().getParcelableArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categoria_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setup(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof ItemTapListener)) {
            throw new ClassCastException("Actividad padre no implementa ItemTapListener");
        }
        itemTapListener = (ItemTapListener)context;
    }

    private void setup(@NonNull View view) {
        setupPointListView(view);
    }

    private void setupPointListView(View view) {
        RecyclerView rvPoints = view.findViewById(R.id.rv_points);
        categmainAdapter = new CategoriaMainAdapter(mcategoriasList, itemTapListener);
        rvPoints.setAdapter(categmainAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rvPoints.setLayoutManager(layoutManager);
        rvPoints.setHasFixedSize(true);
    }
}