package com.singularitycoder.folkcaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BulkSmsActivity extends AppCompatActivity {

    Toolbar toolbarBulkSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
