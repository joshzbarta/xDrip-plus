package com.eveningoutpost.dexdrip.ui.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

import com.eveningoutpost.dexdrip.R;

// jamorham

// ask confirmation, run a runnable

public class GenericConfirmDialog {


    public static void show(final AppCompatActivity activity, String title, String message, Runnable runnable) {

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                runnable.run();
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        // apparently possible dialog is already showing, probably due to hash code
        try {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            //
        }
        dialog.show();
    }
}

