package com.example.anuin.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anuin.R;
import com.example.anuin.order.OrderProcessActivity;
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
    public void onBindViewHolder(@NonNull final vHolder vHolder, final int i) {
        vHolder.tvkode.setText("Kode Pemesanan #ABC1123");
        vHolder.tvmotode.setText("perbaikan / instalasi");
        vHolder.tvtype.setText("AC");
        vHolder.tvTime.setText("10 FEB 2020 14:30 WIB");
        vHolder.tvToko.setText("00:59:10");
        if (i%2!=0){
            vHolder.btnWait.setText("PROSES");
            vHolder.btnWait.setBackgroundColor(Color.parseColor("#27AE60"));
            vHolder.btnWait.setTextColor(Color.rgb(255,255,255));
        }
        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (i%2!=0){
                    intent = new Intent(context, OrderProcessActivity.class);
                }else{
                    intent = new Intent(context, OrderWaitingActivity.class);
                }
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
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
