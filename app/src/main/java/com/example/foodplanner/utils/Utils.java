package com.example.foodplanner.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class Utils {

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }

}
