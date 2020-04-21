package com.example.anuin.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.order.interfaces.PaymentBankInterface;
import com.example.anuin.order.model.PaymentBank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentBankAdapter extends RecyclerView.Adapter<PaymentBankAdapter.viewHolder> {
    Context context;
    List<PaymentBank.DATABean.PaymentMethodBean> paymentBanks;
    PaymentBankInterface onItemClick;
    private int lastSelectedPosition = -1;

    public PaymentBankAdapter(Context context, List<PaymentBank.DATABean.PaymentMethodBean> paymentBanks, PaymentBankInterface onItemClick) {
        this.context = context;
        this.paymentBanks = paymentBanks;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_paymentlist, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtNamaBank.setText(paymentBanks.get(position).getTitle());
        holder.chkPaymentBank.setChecked(lastSelectedPosition == position);
        holder.chkPaymentBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();

                onItemClick.onItemClick(paymentBanks.get(position).getId(), true);
            }
        });
        if (position == 0 && lastSelectedPosition == -1){
            holder.chkPaymentBank.setChecked(true);
            onItemClick.onItemClick(paymentBanks.get(position).getId(), true);
        }
    }

    @Override
    public int getItemCount() {
        return (paymentBanks != null ? paymentBanks.size() : 0);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNamaBank)
        TextView txtNamaBank;
        @BindView(R.id.chkPaymentBank)
        CheckBox chkPaymentBank;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (lastSelectedPosition == -1){
                onItemClick.onItemClick(-1, false);
            }
        }
    }
}
