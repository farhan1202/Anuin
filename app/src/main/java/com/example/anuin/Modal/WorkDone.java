package com.example.anuin.Modal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.anuin.R;
import com.example.anuin.ReviewActivity;

import java.util.zip.Inflater;

public class WorkDone extends BottomSheetDialogFragment {
Button btnWorkBelum,btnWorkSudah;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.work_done_layout,container,false);
        btnWorkSudah = view.findViewById(R.id.btnWorkSudah);
        btnWorkBelum = view.findViewById(R.id.btnWorkBelum);
        btnWorkSudah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });
        btnWorkBelum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
