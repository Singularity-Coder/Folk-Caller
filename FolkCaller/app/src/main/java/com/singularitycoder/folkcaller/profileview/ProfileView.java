package com.singularitycoder.folkcaller.profileview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.singularitycoder.folkcaller.BulkSmsActivity;
import com.singularitycoder.folkcaller.Helper;
import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class ProfileView extends AppCompatActivity {

    ConstraintLayout personActionsContainer;
    ConstraintLayout personInfoContainer;
    ConstraintLayout enterCommentBoxContainer;
    ConstraintLayout contactInfoContainer;
    ConstraintLayout calledByContainer;
    ConstraintLayout commentsContainer;
    ConstraintLayout profileActionsContainer;

    RecyclerView mRecyclerView, commentRecyclerView;
    AdapterProfileViewCalledBy mAdapterProfileViewCalledBy;
    AdapterProfileViewComments commentAdapter;
    ArrayList<ModelProfileView> mArrayList;
    ArrayList<ModelProfileView> commentList;

    Button btnSendBulkSms, btnMakeBulkCalls, btnAddAdmins, btnUploadContacts, btnDownloadContacts, btnAssignCallingTask;
    TextView tvShowMoreActions, tvEditMyDetails, tvEditContactDetails;
    EditText etEnterComment;
    ImageView imgSendComment;

    ImageView actionCall;
    ImageView actionWhatsApp;
    ImageView actionSms;
    ImageView actionEmail;
    ImageView actionShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatuBarColor();
        setContentView(R.layout.activity_profile_view);
        initToolBar();
        instantiations();
        showMoreActions();
        setUpCalledByList();
        setUpCommentsList();
        addComment();
        profileConditions();
    }

    private void instantiations() {
        btnSendBulkSms = findViewById(R.id.btn_send_bulk_sms);
        btnSendBulkSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileView.this, BulkSmsActivity.class));
            }
        });
        btnMakeBulkCalls = findViewById(R.id.btn_make_bulk_calls);
        btnAddAdmins = findViewById(R.id.btn_admin_add_admins);
        btnUploadContacts = findViewById(R.id.btn_profile_upload_contacts);
        btnDownloadContacts = findViewById(R.id.btn_profile_download_contacts);
        btnAssignCallingTask = findViewById(R.id.btn_admin_assign_calling_task);

        // Constraint Layouts
        personActionsContainer = findViewById(R.id.con_lay_admin_assign_task);
        personInfoContainer = findViewById(R.id.con_lay_caller_details);
        enterCommentBoxContainer = findViewById(R.id.con_lay_contact_enter_comment);
        contactInfoContainer = findViewById(R.id.con_lay_contact_details);
        calledByContainer = findViewById(R.id.con_lay_contact_activity);
        commentsContainer = findViewById(R.id.con_lay_contact_comments);
        profileActionsContainer = findViewById(R.id.con_lay_profile_action_icons);

        tvEditMyDetails = findViewById(R.id.tv_profile_my_details_edit);
        tvEditMyDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMyDetailsDialogFunc(ProfileView.this);
            }
        });
        tvEditContactDetails = findViewById(R.id.tv_profile_contact_details_edit);
        tvEditContactDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContactDetailsDialogFunc(ProfileView.this);
            }
        });
        etEnterComment = findViewById(R.id.et_profile_contact_enter_comment);
        imgSendComment = findViewById(R.id.img_profile_contact_send_comment_btn);

        actionCall = findViewById(R.id.img_call);
        actionCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "9535509155";
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                callIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(callIntent);
            }
        });
        actionWhatsApp = findViewById(R.id.img_whatsapp);
        actionWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                try {
                    // checks if such an app exists or not
                    packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    String phone = "9535509155";
                    Uri uri = Uri.parse("smsto:" + phone);
                    Intent whatsAppIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    whatsAppIntent.setPackage("com.whatsapp");
                    startActivity(Intent.createChooser(whatsAppIntent, "Dummy Title"));
                } catch (PackageManager.NameNotFoundException e) {
                    new Helper().toast("WhatsApp not found. Install from playstore.", getApplicationContext(), 1);
                    Uri uri = Uri.parse("market://details?id=com.whatsapp");
                    Intent openPlayStore = new Intent(Intent.ACTION_VIEW, uri);
                    openPlayStore.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    startActivity(openPlayStore);
                }
            }
        });
        actionSms = findViewById(R.id.img_sms);
        actionSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "9535509155";
                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", phone);
                smsIntent.putExtra("sms_body", "Message Body check");
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                if (smsIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(smsIntent);
                }
            }
        });
        actionEmail = findViewById(R.id.img_email);
        actionEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "name@emailaddress.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Follow Up");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Contact, this is telecaller...");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        actionShare = findViewById(R.id.img_share);
        actionShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.singularitycoder.com");
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(Intent.createChooser(shareIntent, "Share to"));
            }
        });
    }

    private void setUpCalledByList() {
        mArrayList = new ArrayList<>();
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM"));
        mRecyclerView = findViewById(R.id.recycler_activity_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true) {
            @Override
            public boolean canScrollVertically () {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mAdapterProfileViewCalledBy = new AdapterProfileViewCalledBy(mArrayList, this);
        mAdapterProfileViewCalledBy.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapterProfileViewCalledBy);
    }

    private void setUpCommentsList() {
        commentList = new ArrayList<>();
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));

        commentRecyclerView = findViewById(R.id.recycler_comments_list);
        LinearLayoutManager commentLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        commentRecyclerView.setLayoutManager(commentLayoutManager);
        commentRecyclerView.setHasFixedSize(true);
        commentRecyclerView.setItemViewCacheSize(20);
        commentRecyclerView.setDrawingCacheEnabled(true);
        commentRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        commentAdapter = new AdapterProfileViewComments(commentList, this);
        commentAdapter.setHasStableIds(true);
        commentRecyclerView.setAdapter(commentAdapter);
    }

    private void addComment() {
        imgSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", etEnterComment.getText().toString()));
                AdapterProfileViewComments adapterProfileViewComments = new AdapterProfileViewComments(commentList, getApplicationContext());
                adapterProfileViewComments.notifyDataSetChanged();
                etEnterComment.setText("");
                new Helper().toast("Comment Added: " + etEnterComment.getText().toString(), getApplicationContext(), 1);
            }
        });
    }

    private void profileConditions() {
        Intent catchIntent = getIntent();
        String keyContact = catchIntent.getStringExtra("openContact");
        String keyCaller = catchIntent.getStringExtra("openCaller");
        String keyAdmin = catchIntent.getStringExtra("openAdmin");
        String keyProfile = catchIntent.getStringExtra("openMyProfile");

        if (keyAdmin != null) {
            if (keyAdmin.equals("ADMIN")) {
                enterCommentBoxContainer.setVisibility(View.GONE);
                contactInfoContainer.setVisibility(View.GONE);
                calledByContainer.setVisibility(View.GONE);
                commentsContainer.setVisibility(View.GONE);
                personActionsContainer.setVisibility(View.GONE);
                tvEditMyDetails.setVisibility(View.GONE);
            }
        }

        if (keyCaller != null) {
            if (keyCaller.equals("CALLER")) {
                enterCommentBoxContainer.setVisibility(View.GONE);
                contactInfoContainer.setVisibility(View.GONE);
                calledByContainer.setVisibility(View.GONE);
                commentsContainer.setVisibility(View.GONE);
                personActionsContainer.setVisibility(View.GONE);
                tvEditMyDetails.setVisibility(View.GONE);
//                btnAddAdmins.setVisibility(View.GONE);
//                btnUploadContacts.setVisibility(View.GONE);
//                btnDownloadContacts.setVisibility(View.GONE);
//                btnAssignCallingTask.setVisibility(View.GONE);
//                tvShowMoreActions.setVisibility(View.GONE);
            }
        }

        if (keyContact != null) {
            if (keyContact.equals("CONTACT")) {
                personActionsContainer.setVisibility(View.GONE);
                personInfoContainer.setVisibility(View.GONE);
            }
        }

        if (keyProfile != null) {
            if (keyProfile.equals("MYPROFILE")) {
                enterCommentBoxContainer.setVisibility(View.GONE);
                contactInfoContainer.setVisibility(View.GONE);
                calledByContainer.setVisibility(View.GONE);
                commentsContainer.setVisibility(View.GONE);
                profileActionsContainer.setVisibility(View.GONE);
            }
        }
    }

    private void showMoreActions() {
        tvShowMoreActions = findViewById(R.id.tv_show_more_actions);
        tvShowMoreActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvShowMoreActions.getText().toString().toLowerCase().equals("show more")) {
                    btnAddAdmins.setVisibility(View.VISIBLE);
                    btnUploadContacts.setVisibility(View.VISIBLE);
                    btnDownloadContacts.setVisibility(View.VISIBLE);
                    btnAssignCallingTask.setVisibility(View.VISIBLE);
                    tvShowMoreActions.setText("SHOW LESS");
                } else if (tvShowMoreActions.getText().toString().toLowerCase().equals("show less")) {
                    btnAddAdmins.setVisibility(View.GONE);
                    btnUploadContacts.setVisibility(View.GONE);
                    btnDownloadContacts.setVisibility(View.GONE);
                    btnAssignCallingTask.setVisibility(View.GONE);
                    tvShowMoreActions.setText("SHOW MORE");
                }
            }
        });
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

    public void editMyDetailsDialogFunc(Activity activity) {
        final Dialog editMyDetailsDialog = new Dialog(activity);
        editMyDetailsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        editMyDetailsDialog.setCancelable(true);
        editMyDetailsDialog.setContentView(R.layout.dialog_edit_my_details);

        Rect displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        editMyDetailsDialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), editMyDetailsDialog.getWindow().getAttributes().height);

        editMyDetailsDialog.show();
    }

    public void editContactDetailsDialogFunc(Activity activity) {
        final Dialog editContactDetailsDialog = new Dialog(activity);
        editContactDetailsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        editContactDetailsDialog.setCancelable(true);
        editContactDetailsDialog.setContentView(R.layout.dialog_edit_contact_details);

        Rect displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        editContactDetailsDialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), editContactDetailsDialog.getWindow().getAttributes().height);

        editContactDetailsDialog.show();
    }
}
