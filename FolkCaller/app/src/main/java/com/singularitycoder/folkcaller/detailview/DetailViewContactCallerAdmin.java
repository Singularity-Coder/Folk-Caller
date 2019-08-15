package com.singularitycoder.folkcaller.detailview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class DetailViewContactCallerAdmin extends AppCompatActivity {

    Button btnSendBulkSms, btnMakeBulkCalls, btnAddAdmins, btnUploadContacts, btnAssignCallingTask;

    RecyclerView mRecyclerView, commentRecyclerView;
    AdapterDetailViewCalledBy mAdapterDetailViewCalledBy;
    AdapterDetailViewComments commentAdapter;
    ArrayList<ModelDetailViewCalledBy> mArrayList;
    ArrayList<ModelDetailViewComments> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatuBarColor();
        setContentView(R.layout.activity_detail_view_contact_caller_admin);
        initToolBar();

        btnSendBulkSms = findViewById(R.id.btn_send_bulk_sms);
        btnMakeBulkCalls = findViewById(R.id.btn_make_bulk_calls);
        btnAddAdmins = findViewById(R.id.btn_admin_add_admins);
        btnUploadContacts = findViewById(R.id.btn_profile_upload_contacts);
        btnAssignCallingTask = findViewById(R.id.btn_admin_assign_calling_task);

        final TextView tvShowMoreActions = findViewById(R.id.tv_show_more_actions);
        tvShowMoreActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvShowMoreActions.getText().toString().toLowerCase().equals("show more")) {
                    btnAddAdmins.setVisibility(View.VISIBLE);
                    btnUploadContacts.setVisibility(View.VISIBLE);
                    btnAssignCallingTask.setVisibility(View.VISIBLE);
                    tvShowMoreActions.setText("SHOW LESS");
                } else if (tvShowMoreActions.getText().toString().toLowerCase().equals("show less")) {
                    btnAddAdmins.setVisibility(View.GONE);
                    btnUploadContacts.setVisibility(View.GONE);
                    btnAssignCallingTask.setVisibility(View.GONE);
                    tvShowMoreActions.setText("SHOW MORE");
                }
            }
        });

        mArrayList = new ArrayList<>();
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelDetailViewCalledBy(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mRecyclerView = findViewById(R.id.recycler_called_by_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mAdapterDetailViewCalledBy = new AdapterDetailViewCalledBy(mArrayList, this);
        mAdapterDetailViewCalledBy.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapterDetailViewCalledBy);


        commentList = new ArrayList<>();
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelDetailViewComments(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentRecyclerView = findViewById(R.id.recycler_comments_list);
        LinearLayoutManager commentLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        commentRecyclerView.setLayoutManager(commentLayoutManager);
        commentRecyclerView.setHasFixedSize(true);
        commentRecyclerView.setItemViewCacheSize(20);
        commentRecyclerView.setDrawingCacheEnabled(true);
        commentRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        commentAdapter = new AdapterDetailViewComments(commentList, this);
        commentAdapter.setHasStableIds(true);
        commentRecyclerView.setAdapter(commentAdapter);


    }

    private void setStatuBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  // clear FLAG_TRANSLUCENT_STATUS flag:
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));   // change the color
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void initToolBar() {
        Toolbar viewEventToolbar = findViewById(R.id.toolbar_create_event);
        viewEventToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewEventToolbar.setElevation(10);
        }
        setSupportActionBar(viewEventToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
