package com.example.anuin.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.anuin.R;
import com.example.anuin.home.model.Banner;
import com.example.anuin.introNlogin.apihelper.ApiInterface;
import com.example.anuin.introNlogin.apihelper.UtilsApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                .centerCrop()
                .into(imageView);

        //imageView.setImageResource(R.drawable.logoanuin);

        container.addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
