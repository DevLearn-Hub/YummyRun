package com.shop.yummyrun.ui.checkout;

import android.app.AlertDialog;
import android.content.Context;

public class ConfirmOrderDialog {
    public static void show(Context context, final Runnable onConfirm, final Runnable onCancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Вы уверены, что хотите оформить заказ?")
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, id) -> onConfirm.run())
                .setNegativeButton("Нет", (dialog, id) -> onCancel.run());
        AlertDialog alert = builder.create();
        alert.show();
    }
}
