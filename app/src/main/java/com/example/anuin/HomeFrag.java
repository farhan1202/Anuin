package com.example.anuin;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.Adapter.MekanikAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {
MekanikAdapter mekanikAdapter;
RecyclerView recyclerMekanik, recyclerDekor;
Context context;

    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerMekanik = view.findViewById(R.id.recyclerElektronik);
        recyclerDekor = view.findViewById(R.id.recyclerDekor);
        context = view.getContext();

        mekanikAdapter = new MekanikAdapter(context);
        recyclerMekanik.setAdapter(mekanikAdapter);
        recyclerMekanik.setLayoutManager(new GridLayoutManager(context, 2));

        recyclerDekor.setAdapter(mekanikAdapter);
        recyclerDekor.setLayoutManager(new GridLayoutManager(context, 2));

        return view;
    }
}
