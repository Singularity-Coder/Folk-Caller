package com.singularitycoder.folkcaller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Helper extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void statusBarStuff(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  // clear FLAG_TRANSLUCENT_STATUS flag:
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.setStatusBarColor(ContextCompat.getColor(activity, color));   // change the color
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void comingSoonDialog(Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Coming Soon")
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                    }
                })
                .show();
    }

    public void toast(String msg, Context context, int length) {
        Toast.makeText(context, msg, length).show();
    }

    private void dialogSingleChoice(final String[] stringArray, final String dialogTitle) {
        final String[] single_choice_selected = {stringArray[0]};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setSingleChoiceItems(stringArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                single_choice_selected[0] = stringArray[i];
            }
        });

        builder.setPositiveButton("okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("TAG", "selected language is-->>" + single_choice_selected[0]);
                switch (dialogTitle) {
                    case "con 1":
                        TextView s1 = null;
                        s1.setText(single_choice_selected[0]);
                        break;
                    case "con 2":
                        TextView s2 = null;
                        s2.setText(single_choice_selected[0]);
                        break;
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }


    public void customDialog(Activity activity, int layoutId) {
        final Dialog fingerPrintDialog = new Dialog(activity);
        fingerPrintDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fingerPrintDialog.setCancelable(true);
        fingerPrintDialog.setContentView(layoutId);

        Rect displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        fingerPrintDialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), fingerPrintDialog.getWindow().getAttributes().height);

        fingerPrintDialog.show();
    }
}
