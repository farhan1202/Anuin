package com.example.anuin.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anuin.R;
import com.example.anuin.home.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {


    private Context context;
    private List<Category.DATABean> categories;

    public CategoryAdapter(Context context, List<Category.DATABean> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_group, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.textCategoryName.setText(categories.get(i).getMaster_category_title());
        List<Category.DATABean.SubCategoryBean> subCategoryBeans = categories.get(i).getSub_category();
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(context, subCategoryBeans);
        viewHolder.recyclerElektronik.setAdapter(subCategoryAdapter);
        viewHolder.recyclerElektronik.setLayoutManager(new GridLayoutManager(context, 2));
        viewHolder.recyclerElektronik.setHasFixedSize(true);
        viewHolder.recyclerElektronik.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return (categories != null ? categories.size() : 0 );
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textCategoryName)
        TextView textCategoryName;
        @BindView(R.id.recyclerElektronik)
        RecyclerView recyclerElektronik;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
