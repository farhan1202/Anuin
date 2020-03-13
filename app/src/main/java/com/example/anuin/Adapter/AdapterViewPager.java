package com.example.anuin.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.anuin.R;
import com.example.anuin.home.model.Banner;

import java.util.ArrayList;

public class AdapterViewPager extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Banner.DATABean> banners;

    public AdapterViewPager(Context context, ArrayList<Banner.DATABean> banners) {
        this.context = context;
        this.banners = banners;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_viewpager, container, false);


        ImageView imageView;
        imageView = view.findViewById(R.id.gambar);

        Banner.DATABean banner = banners.get(position);
        Glide.with(context)
                .load(banner.getBanner_image())
                .placeholder(new ColorDrawable(Color.parseColor("#FB8E03")))
                .centerCrop()
                .into(imageView);

        container.addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
