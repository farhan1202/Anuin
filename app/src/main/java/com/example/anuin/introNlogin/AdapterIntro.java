package com.example.anuin.introNlogin;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anuin.R;
import com.example.anuin.introNlogin.model.Walkthrough;

import java.util.ArrayList;

public class AdapterIntro  extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Walkthrough> walkthroughs;

    public AdapterIntro(Context context, ArrayList<Walkthrough> walkthroughs) {
        this.context = context;
        this.walkthroughs = walkthroughs;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slideintro1, container, false);

        TextView txtTitle = view.findViewById(R.id.txtTitle1);
        TextView txtDesc = view.findViewById(R.id.txtDesc1);
        ImageView imageView = view.findViewById(R.id.imgSlide1);

        Walkthrough walkthrough = walkthroughs.get(position);

        txtTitle.setText(walkthrough.getTitle());
        txtDesc.setText(Html.fromHtml(walkthrough.getDesc()));
        Glide.with(context)
                .load(walkthrough.getImgSrc())
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }


}
