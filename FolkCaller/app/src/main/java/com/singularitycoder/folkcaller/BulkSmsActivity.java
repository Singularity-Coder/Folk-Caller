package com.singularitycoder.folkcaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class BulkSmsActivity extends AppCompatActivity {

    Toolbar toolbarBulkSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  // clear FLAG_TRANSLUCENT_STATUS flag:
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));   // change the color
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_bulk_sms);

        initToolBar();
    }

    private void initToolBar() {
        toolbarBulkSms = findViewById(R.id.toolbar_bulk_sms);
        toolbarBulkSms.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarBulkSms.setElevation(10);
        }
        setSupportActionBar(toolbarBulkSms);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Send Bulk SMS");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bulk_sms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send_bulk_sms:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
