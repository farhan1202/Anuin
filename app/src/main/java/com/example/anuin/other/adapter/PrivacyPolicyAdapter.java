package com.example.anuin.other.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.other.model.PrivacyPolicyModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyPolicyAdapter extends RecyclerView.Adapter<PrivacyPolicyAdapter.vHolder> {

    private Context context;
    private List<PrivacyPolicyModel.DATABean> list;

    public PrivacyPolicyAdapter(Context context, List<PrivacyPolicyModel.DATABean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_privacy, viewGroup, false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vHolder vholder, int i) {
        vholder.tvTitle.setText(list.get(i).getTitle());
        vholder.tvDesc.setText(list.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class vHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDesc)
        TextView tvDesc;
        public vHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
