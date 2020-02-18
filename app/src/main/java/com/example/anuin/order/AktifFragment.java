package com.example.anuin.order;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.Adapter.WaitingOrderAdapter;
import com.example.anuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AktifFragment extends Fragment {
    RecyclerView rvOrderAktif;
    WaitingOrderAdapter waitingOrderAdapter;
    Context context;



    public AktifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_aktif, container, false);
        rvOrderAktif=view.findViewById(R.id.rvOrderAktif);
        context=view.getContext();
        waitingOrderAdapter= new WaitingOrderAdapter(context);
        rvOrderAktif.setLayoutManager(new LinearLayoutManager(context));
        rvOrderAktif.setAdapter(waitingOrderAdapter);
        return view;

    }

}
