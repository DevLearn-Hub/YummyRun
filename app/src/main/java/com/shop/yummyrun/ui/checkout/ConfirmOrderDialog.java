package com.shop.yummyrun.ui.checkout;

import android.app.AlertDialog;
import android.content.Context;

public class ConfirmOrderDialog {

    public interface ConfirmListener {
        void onConfirm();
    }

    public interface CancelListener {
        void onCancel();
    }

    public static void show(Context context, ConfirmListener confirmListener, CancelListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Подтвердить заказ")
                .setMessage("Вы уверены, что хотите оформить заказ?")
                .setPositiveButton("Да", (dialog, which) -> {
                    confirmListener.onConfirm();
                    dialog.dismiss();
                })
                .setNegativeButton("Нет", (dialog, which) -> {
                    cancelListener.onCancel();
                    dialog.dismiss();
                })
                .show();
    }
}
