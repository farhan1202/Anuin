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
import com.example.anuin.order.model.OrderList;

import java.util.List;

public class WaitingOrderAdapter extends RecyclerView.Adapter<WaitingOrderAdapter.vHolder> {
    Context context;
    List<OrderList.DATABean> bookingList;

    public WaitingOrderAdapter(Context context, List<OrderList.DATABean> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_order, viewGroup, false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final vHolder vHolder, final int i) {
        vHolder.tvkode.setText(bookingList.get(i).getBooking_code().getCode_name());
        vHolder.tvmotode.setText(bookingList.get(i).getDetail_pekerjaan());
        vHolder.tvtype.setText(bookingList.get(i).getProduct_jasa().getCategory().getCategory_title());
        vHolder.tvTime.setText(bookingList.get(i).getWork_date());
        vHolder.tvToko.setText("00:59:10");
        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderWaitingActivity.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
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
