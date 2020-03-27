package com.example.anuin.profil.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.profil.DetailAddressActivity;
import com.example.anuin.profil.model.Address;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.viewHolder> {

    private Context context;
    private List<Address.DATABean> dataBeans;

    public AddressAdapter(Context context, List<Address.DATABean> dataBeans) {
        this.context = context;
        this.dataBeans = dataBeans;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_address, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtAlamat.setText(dataBeans.get(position).getAlamat());
        holder.detailAlamat.setText(dataBeans.get(position).getLokasi_maps());
        holder.layoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailAddressActivity.class);
                intent.putExtra("eID", dataBeans.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataBeans != null ? dataBeans.size() : 0);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtAlamat)
        TextView txtAlamat;
        @BindView(R.id.detailAlamat)
        TextView detailAlamat;
        @BindView(R.id.layoutClick)
        LinearLayout layoutClick;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
