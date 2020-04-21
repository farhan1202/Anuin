package com.example.anuin.order;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anuin.Modal.LoginDialog;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.R;
import com.example.anuin.order.adapter.PagerOrderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFrag extends Fragment {

    public OrderFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_order, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.vPager);
        TabLayout tabLayout = view.findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);

        PagerOrderAdapter adapter = new PagerOrderAdapter(getChildFragmentManager());
        adapter.addFrag(new AktifFragment(), "AKTIF");
        adapter.addFrag(new SelesaiFragment(), "SELESAI");
        viewPager.setAdapter(adapter);


    }

}
