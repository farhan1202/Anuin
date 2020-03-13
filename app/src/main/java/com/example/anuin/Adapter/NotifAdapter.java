package com.example.anuin.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anuin.R;

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.NotifHolder> {
    @NonNull
    @Override
    public NotifAdapter.NotifHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notif,viewGroup,false);

        return new NotifHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifAdapter.NotifHolder notifHolder, int i) {
        for (int j = 0; j <5 ; j++) {
            notifHolder.txtJudulNotif.setText("Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet");
            notifHolder.txtIsiNotif.setText("Lorem Ipsum Dolor Sit Amet Dolor Sit Amet hahaha");
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class NotifHolder extends RecyclerView.ViewHolder {
        TextView txtJudulNotif,txtIsiNotif;
        public NotifHolder(@NonNull View itemView) {
            super(itemView);
        txtJudulNotif = itemView.findViewById(R.id.txtJudulNotif);
        txtIsiNotif = itemView.findViewById(R.id.txtIsiNotif);
        }
    }
}
