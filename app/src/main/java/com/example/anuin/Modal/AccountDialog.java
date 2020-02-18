package com.example.anuin.Modal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.anuin.R;

public class AccountDialog extends BottomSheetDialogFragment {
    //private AccountDialogListener listener;
    EditText editData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_layout,container,false);
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
