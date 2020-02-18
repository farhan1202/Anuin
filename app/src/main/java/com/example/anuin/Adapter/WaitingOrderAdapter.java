package com.example.anuin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anuin.R;
import com.example.anuin.order.OrderWaitingActivity;

public class WaitingOrderAdapter extends RecyclerView.Adapter<WaitingOrderAdapter.vHolder> {
    Context context;

    public WaitingOrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_order, viewGroup, false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final vHolder vHolder, int i) {
        vHolder.tvkode.setText("Kode Pemesanan #ABC1123");
        vHolder.tvmotode.setText("perbaikan / instalasi");
        vHolder.tvtype.setText("AC");
        vHolder.tvTime.setText("10 FEB 2020 14:30 WIB");
        vHolder.tvToko.setText("00:59:10");
        vHolder.btnWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderWaitingActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class vHolder extends RecyclerView.ViewHolder {
        TextView tvkode,tvmotode,tvtype,tvTime,tvToko;
        Button btnWait;
        CardView cardView;
        public vHolder(@NonNull View itemView) {
            super(itemView);
            tvkode=itemView.findViewById(R.id.tvKode);
            tvmotode=itemView.findViewById(R.id.tvMotode);
            tvtype=itemView.findViewById(R.id.tvTipe);
            tvTime=itemView.findViewById(R.id.tvTimeNDate);
            tvToko=itemView.findViewById(R.id.tvToko);
            btnWait=itemView.findViewById(R.id.btnWait);
            cardView=itemView.findViewById(R.id.cvOrder);
        }
    }
}
