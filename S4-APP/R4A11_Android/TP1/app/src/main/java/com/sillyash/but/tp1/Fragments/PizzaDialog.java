package com.sillyash.but.tp1.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class PizzaDialog extends DialogFragment {
    public static String TAG = "PizzaDialog";
    private String msg;

    public PizzaDialog(String msg) {
        this.msg = msg;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(this.msg)
                .setPositiveButton("OK", (dialog, which) -> {} )
                .create();
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }
}
