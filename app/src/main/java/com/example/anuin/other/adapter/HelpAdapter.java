package com.example.anuin.other.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.other.model.HelpsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.vHolder> {

    private List<HelpsModel.DATABean> listHelp;
    private Context context;

    private static int currentPosition = -1;

    public HelpAdapter(List<HelpsModel.DATABean> listHelp, Context context) {
        this.listHelp = listHelp;
        this.context = context;
    }

    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_group_help, parent, false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vHolder holder, int i) {
        holder.tvTitle.setText(listHelp.get(i).getTitle());
        holder.linearLayout.setVisibility(View.GONE);

        List<HelpsModel.DATABean.HelpsBean> helpsBeans = listHelp.get(i).getHelps();
        HelpsChildAdapter helpsChildAdapter = new HelpsChildAdapter(context, helpsBeans);
        holder.rvChild.setAdapter(helpsChildAdapter);
        holder.rvChild.setLayoutManager(new LinearLayoutManager(context));
        holder.rvChild.setHasFixedSize(true);
        holder.rvChild.setNestedScrollingEnabled(false);

        if (currentPosition == i) {

            holder.linearLayout.setVisibility(View.VISIBLE);

        }

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPosition == i) {
                    currentPosition = -1;
                    holder.linearLayout.setVisibility(View.GONE);

                } else {
                    currentPosition = i;

                }

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listHelp.size();
    }

    public class vHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
        @BindView(R.id.rvChild)
        RecyclerView rvChild;

        public vHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
