package com.example.anuin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anuin.home.DetailJasaActivity;
import com.example.anuin.R;

public class MekanikAdapter extends RecyclerView.Adapter<MekanikAdapter.viewHolder> {

    Context context;

    public MekanikAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MekanikAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MekanikAdapter.viewHolder viewHolder, int i) {
        viewHolder.textView.setText("Sample");
        Glide
                .with(context)
                .load("https://freeiconshop.com/wp-content/uploads/edd/microwave-flat.png")
                .centerCrop()
                .into(viewHolder.imageView);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailJasaActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgRow);
            textView = itemView.findViewById(R.id.txtNamaRow);
            cardView = itemView.findViewById(R.id.cardViewRow);
        }
    }
}
