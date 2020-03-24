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

public class UsernameDialog extends BottomSheetDialogFragment {
    @BindView(R.id.editUsername)
    EditText editUsername;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.username_layout,container,false);
        ButterKnife.bind(this, view);
        String username = getArguments().getString("username");
        editUsername.setText(username);
        return view;
    }
}
