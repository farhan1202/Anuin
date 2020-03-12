package com.example.anuin.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anuin.R;
import com.example.anuin.home.interfaces.ProductJasaInterface;
import com.example.anuin.home.model.ProductJasa;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JasaAdapter extends RecyclerView.Adapter<JasaAdapter.viewHolder> {

    private Context context;
    private List<ProductJasa.DATABean.ProductJasaBean> dataBeanProductJasa;
    private ProductJasaInterface productJasaInterface;

    private int lastSelectedPosition = -1;

    public JasaAdapter(Context context, List<ProductJasa.DATABean.ProductJasaBean> dataBeanProductJasa, ProductJasaInterface productJasaInterface) {
        this.context = context;
        this.dataBeanProductJasa = dataBeanProductJasa;
        this.productJasaInterface = productJasaInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_jasa, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, final int i) {
        viewHolder.jasaTitle.setText(dataBeanProductJasa.get(i).getProduct_jasa_title());
        viewHolder.jasaPrice.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(dataBeanProductJasa.get(i).getProduct_jasa_harga())+""));
        viewHolder.chk.setChecked(lastSelectedPosition == i);
        viewHolder.chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastSelectedPosition = viewHolder.getAdapterPosition();
                notifyDataSetChanged();
                productJasaInterface.onItemClick(i, true);
            }
        });
        if (i == 0 && lastSelectedPosition == -1){
            viewHolder.chk.setChecked(true);
            productJasaInterface.onItemClick(i, true);
        }
    }

    @Override
    public int getItemCount() {
        return (dataBeanProductJasa != null ? dataBeanProductJasa.size() : 0);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jasaTitle)
        TextView jasaTitle;
        @BindView(R.id.jasaPrice)
        TextView jasaPrice;
        @BindView(R.id.chk)
        CheckBox chk;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            /*chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });*/
            if (lastSelectedPosition == -1){
                productJasaInterface.onItemClick(-1, false);
            }
        }
    }
}
