package com.example.anuin.other.submenusetting;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.other.model.TermUse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class rowTermAdapter extends RecyclerView.Adapter<rowTermAdapter.viewHolder> {
    private Context context;
    private List<TermUse.DATABean> dataBeanList;

    public rowTermAdapter(Context context, List<TermUse.DATABean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_term,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        holder.titleTerm.setText(dataBeanList.get(position).getTitle());
        holder.contentTerm.setText(dataBeanList.get(position).getContent());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.contentTerm.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    @Override
    public int getItemCount() {
        return (dataBeanList != null ? dataBeanList.size() : 0);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitleTerm)
        TextView titleTerm;
        @BindView(R.id.txtContentTerm)
        TextView contentTerm;
        @BindView(R.id.expandableLayout)
        RelativeLayout expandableLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
