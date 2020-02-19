package com.example.anuin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.order.adapter.PagerOrderAdapter;
import com.example.anuin.order.AktifFragment;
import com.example.anuin.order.SelesaiFragment;


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
