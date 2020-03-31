package com.example.anuin.other.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.other.model.HelpsModel;
import com.example.anuin.other.setting.DetailChildActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpsChildAdapter extends RecyclerView.Adapter<HelpsChildAdapter.vHolder> {

    private Context context;
    private List<HelpsModel.DATABean.HelpsBean> helpsChildList;

    public HelpsChildAdapter(Context context, List<HelpsModel.DATABean.HelpsBean> helpsChildList) {
        this.context = context;
        this.helpsChildList = helpsChildList;
    }

    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_help, parent, false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vHolder holder, int i) {
        holder.tvTitle.setText(helpsChildList.get(i).getTitle());
        holder.cvChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailChildActivity.class);
                intent.putExtra("TITLE", helpsChildList.get(i).getTitle());
                intent.putExtra("CONTENT", helpsChildList.get(i).getContent());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return helpsChildList.size();
    }

    public class vHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.cvChild)
        CardView cvChild;
        public vHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
