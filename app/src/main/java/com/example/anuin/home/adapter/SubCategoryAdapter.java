package com.example.anuin.home.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anuin.R;
import com.example.anuin.home.DetailJasaActivity;
import com.example.anuin.home.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.viewHolder> {

    private Context context;
    private List<Category.DATABean.SubCategoryBean> subCategoryBeans;

    public SubCategoryAdapter(Context context, List<Category.DATABean.SubCategoryBean> subCategoryBeans) {
        this.context = context;
        this.subCategoryBeans = subCategoryBeans;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, final int i) {
        viewHolder.txtNamaRow.setText(subCategoryBeans.get(i).getCategory_title());
        Glide.with(context)
                .load(subCategoryBeans.get(i).getCategory_image())
                .centerCrop()
                .into(viewHolder.imgRow);
        viewHolder.cardViewRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailJasaActivity.class);
                intent.putExtra("eId", subCategoryBeans.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (subCategoryBeans != null ? subCategoryBeans.size() : 0);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgRow)
        ImageView imgRow;
        @BindView(R.id.txtNamaRow)
        TextView txtNamaRow;
        @BindView(R.id.cardViewRow)
        CardView cardViewRow;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
