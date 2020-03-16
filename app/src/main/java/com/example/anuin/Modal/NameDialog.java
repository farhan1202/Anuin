package com.example.anuin.Modal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anuin.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NameDialog extends BottomSheetDialogFragment {
    @BindView(R.id.editNama)
    EditText editNama;
    //private AccountDialogListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.name_layout, container, false);
        ButterKnife.bind(this, view);
        String nama = getArguments().getString("nama");
        editNama.setText(nama);
        return view;
    }

    /*public interface AccountDialogListener{
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AccountDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"Must Implement Interface");
        }

    }*/
}
