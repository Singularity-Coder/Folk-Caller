package com.singularitycoder.folkcaller.profileview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    ConstraintLayout selectProgramContainer;
    ConstraintLayout reachOverviewContainer;
    ConstraintLayout callerStatsContainer;
    ConstraintLayout doNotDisturbContainer;
    ConstraintLayout programDetailsContainer;
    ConstraintLayout finishContactContainer;

    RecyclerView mRecyclerView, commentRecyclerView;
    AdapterProfileViewActivities mAdapterProfileViewActivities;
    AdapterProfileViewComments commentAdapter;
    ArrayList<ModelProfileView> mArrayList;
    ArrayList<ModelProfileView> commentList;

    TextView tvEditMyDetails, tvEditContactDetails, tvSelectProgram;
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
        setUpActivityOnContactList();
        setUpCommentsList();
        addComment();
        profileConditions();
        setUpAdminActionsList();
        setUpReachOverviewList();
        setUpCallerStatsList();
    }

    private void instantiations() {

        // Constraint Layouts
        personActionsContainer = findViewById(R.id.con_lay_admin_actions);
        personInfoContainer = findViewById(R.id.con_lay_caller_details);
        enterCommentBoxContainer = findViewById(R.id.con_lay_contact_enter_comment);
        contactInfoContainer = findViewById(R.id.con_lay_contact_details);
        calledByContainer = findViewById(R.id.con_lay_contact_activity);
        commentsContainer = findViewById(R.id.con_lay_contact_comments);
        profileActionsContainer = findViewById(R.id.con_lay_profile_action_icons);
        selectProgramContainer = findViewById(R.id.con_lay_select_program);
        reachOverviewContainer = findViewById(R.id.con_lay_contact_overview);
        callerStatsContainer = findViewById(R.id.con_lay_caller_stats);
        doNotDisturbContainer = findViewById(R.id.con_lay_do_not_disturb);
        programDetailsContainer = findViewById(R.id.con_lay_contact_program_details);
        finishContactContainer = findViewById(R.id.con_lay_contact_finish_talking);

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
        tvSelectProgram = findViewById(R.id.tv_select_program);
        tvSelectProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSelectProgram();
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

    private void setUpActivityOnContactList() {
        mArrayList = new ArrayList<>();
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM", "Called", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face2, "Mulan Bennet", "12 July, 4819 @ 6:00 AM", "Edited", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM", "Called", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face3, "Kristen Mona", "12 July, 4819 @ 6:00 AM", "Called", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM", "Messaged", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face2, "Mulan Bennet", "12 July, 4819 @ 6:00 AM", "Edited", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face3, "Kristen Mona", "12 July, 4819 @ 6:00 AM", "Emailed", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face1, "Catherine Bennet", "12 July, 4819 @ 6:00 AM", "WhatsApp", ""));
        mArrayList.add(new ModelProfileView(R.drawable.face2, "Mulan Bennet", "12 July, 4819 @ 6:00 AM", "Called", ""));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRecyclerView = findViewById(R.id.recycler_activity_list);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        mAdapterProfileViewActivities = new AdapterProfileViewActivities(mArrayList, this);
        mAdapterProfileViewActivities.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapterProfileViewActivities);
    }


    private void setUpCommentsList() {
        commentList = new ArrayList<>();
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "1 July, 4819 @ 3:30 PM", "Contact did not respond to the call!"));
        commentList.add(new ModelProfileView(R.drawable.face1, "Catherine Melty", "7 July, 4819 @ 3:30 PM", "Contact did not respond to the call! Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face2, "Raj Kelia", "9 July, 4819 @ 3:30 PM", "Tried 4 times in the afternoon and 2 times in the evening!"));
        commentList.add(new ModelProfileView(R.drawable.face3, "Sylvia Motan", "12 July, 4819 @ 3:30 PM", "No response. Out of station."));

        LinearLayoutManager commentLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        commentRecyclerView = findViewById(R.id.recycler_comments_list);
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
        etEnterComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                imgSendComment.setImageResource(R.drawable.ic_send_grey_24dp);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                imgSendComment.setImageResource(R.drawable.ic_send_black_24dp);
                imgSendComment.isEnabled();
            }
        });
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

    private void setUpAdminActionsList() {
        ArrayList<StatsOrActionsModel> adminActionsList = new ArrayList<>();
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_call_black_24dp, "Make Bulk Calls", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_history_black_24dp, "My Call History", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_sms_black_24dp, "Send Bulk SMS", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_history_black_24dp, "My SMS History", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_person_add__admins_black_24dp, "Add Admins", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_assign_task_black_24dp, "Assign Task", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_assigned_tasks_black_24dp, "Assigned Tasks", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_my_tasks_black_24dp, "My Tasks", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_cloud_upload_black_24dp, "Upload Contacts", "AdminAction"));
        adminActionsList.add(new StatsOrActionsModel(R.drawable.ic_cloud_download_black_24dp, "Download Contacts", "AdminAction"));

        LinearLayoutManager commentLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        RecyclerView adminActionsRecycler = findViewById(R.id.recycler_admin_actions);
        adminActionsRecycler.setLayoutManager(commentLayoutManager);
        adminActionsRecycler.setHasFixedSize(true);
        adminActionsRecycler.setItemViewCacheSize(20);
        adminActionsRecycler.setDrawingCacheEnabled(true);
        adminActionsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        StatsOrActionsAdapter statsOrActionsAdapter = new StatsOrActionsAdapter(adminActionsList, this);
        statsOrActionsAdapter.setHasStableIds(true);

        adminActionsRecycler.setAdapter(statsOrActionsAdapter);
    }

    private void setUpReachOverviewList() {
        ArrayList<StatsOrActionsModel> reachList = new ArrayList<>();
        reachList.add(new StatsOrActionsModel(R.drawable.ic_activity_black_24dp, "Activities", "54", "Reach"));
        reachList.add(new StatsOrActionsModel(R.drawable.ic_comment_black_24dp, "Comments", "14", "Reach"));
        reachList.add(new StatsOrActionsModel(R.drawable.ic_overview_call_black_24dp, "Calls", "34", "Reach"));
        reachList.add(new StatsOrActionsModel(R.drawable.ic_sms_black_24dp, "SMS", "27", "Reach"));
        reachList.add(new StatsOrActionsModel(R.drawable.ic_whatsapp_logo_overview, "WhatsApp Messages", "44", "Reach"));
        reachList.add(new StatsOrActionsModel(R.drawable.ic_overview_email_black_24dp, "Emails", "4", "Reach"));
        reachList.add(new StatsOrActionsModel(R.drawable.ic_overview_share_black_24dp, "Shares", "3", "Reach"));

        LinearLayoutManager commentLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        RecyclerView reachOverviewRecycler = findViewById(R.id.recycler_reach_overview);
        reachOverviewRecycler.setLayoutManager(commentLayoutManager);
        reachOverviewRecycler.setHasFixedSize(true);
        reachOverviewRecycler.setItemViewCacheSize(20);
        reachOverviewRecycler.setDrawingCacheEnabled(true);
        reachOverviewRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        ReachAdapter reachAdapter = new ReachAdapter(reachList, this, "");
        reachAdapter.setHasStableIds(true);

        reachOverviewRecycler.setAdapter(reachAdapter);
    }

    private void setUpCallerStatsList() {
        ArrayList<StatsOrActionsModel> callerStatsList = new ArrayList<>();
        callerStatsList.add(new StatsOrActionsModel(R.drawable.ic_tasks_finished_black_24dp, "Tasks Finished", "24", "CallerStats"));
        callerStatsList.add(new StatsOrActionsModel(R.drawable.ic_pending_black_24dp, "Tasks Pending", "3", "CallerStats"));
        callerStatsList.add(new StatsOrActionsModel(R.drawable.ic_contact_mail_black_24dp, "Contacts Called", "2404", "CallerStats"));
        callerStatsList.add(new StatsOrActionsModel(R.drawable.ic_hourspent_black_24dp, "Hours Spent", "54", "CallerStats"));
        callerStatsList.add(new StatsOrActionsModel(R.drawable.ic_conversions_black_24dp, "Conversions", "1051", "CallerStats"));
        callerStatsList.add(new StatsOrActionsModel(R.drawable.ic_starrank_black_24dp, "Caller Rank", "7", "CallerStats"));

        LinearLayoutManager commentLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        RecyclerView reachOverviewRecycler = findViewById(R.id.recycler_caller_stats);
        reachOverviewRecycler.setLayoutManager(commentLayoutManager);
        reachOverviewRecycler.setHasFixedSize(true);
        reachOverviewRecycler.setItemViewCacheSize(20);
        reachOverviewRecycler.setDrawingCacheEnabled(true);
        reachOverviewRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        CallerStatsAdapter callerStatsAdapter = new CallerStatsAdapter(callerStatsList, this, "");
        callerStatsAdapter.setHasStableIds(true);

        reachOverviewRecycler.setAdapter(callerStatsAdapter);
    }


    private void profileConditions() {
        Intent catchIntent = getIntent();
        String keyContact = catchIntent.getStringExtra("openContact");
        String keyCaller = catchIntent.getStringExtra("openCaller");
        String keyAdmin = catchIntent.getStringExtra("openAdmin");
        String keyProfile = catchIntent.getStringExtra("openMyProfile");

        if (keyAdmin != null) {
            if (keyAdmin.equals("ADMIN")) {
//                enterCommentBoxContainer.setVisibility(View.GONE);
                contactInfoContainer.setVisibility(View.GONE);
                calledByContainer.setVisibility(View.GONE);
                commentsContainer.setVisibility(View.GONE);
                personActionsContainer.setVisibility(View.GONE);
                tvEditMyDetails.setVisibility(View.GONE);
            }
        }

        if (keyCaller != null) {
            if (keyCaller.equals("CALLER")) {
//                enterCommentBoxContainer.setVisibility(View.GONE);
                contactInfoContainer.setVisibility(View.GONE);
                calledByContainer.setVisibility(View.GONE);
                commentsContainer.setVisibility(View.GONE);
                personActionsContainer.setVisibility(View.GONE);
                tvEditMyDetails.setVisibility(View.GONE);
                callerStatsContainer.setVisibility(View.VISIBLE);
            }
        }

        if (keyContact != null) {
            if (keyContact.equals("CONTACT")) {
                personActionsContainer.setVisibility(View.GONE);
                personInfoContainer.setVisibility(View.GONE);
                selectProgramContainer.setVisibility(View.VISIBLE);
                reachOverviewContainer.setVisibility(View.VISIBLE);
                doNotDisturbContainer.setVisibility(View.VISIBLE);
                programDetailsContainer.setVisibility(View.VISIBLE);
                finishContactContainer.setVisibility(View.VISIBLE);
            }
        }

        if (keyProfile != null) {
            if (keyProfile.equals("MYPROFILE")) {
//                enterCommentBoxContainer.setVisibility(View.GONE);
                contactInfoContainer.setVisibility(View.GONE);
                calledByContainer.setVisibility(View.GONE);
                commentsContainer.setVisibility(View.GONE);
                profileActionsContainer.setVisibility(View.GONE);
            }
        }
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

    public void dialogSelectProgram() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Program");
        String[] selectArray = {"Yoga For Happiness", "Zoga For Happiness", "Aoga For Happiness", "Boga For Happiness", "Coga For Happiness"};
        builder.setItems(selectArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        tvSelectProgram.setText("Yoga For Happiness");
                        break;
                    case 1:
                        tvSelectProgram.setText("Zoga For Happiness");
                        break;
                    case 2:
                        tvSelectProgram.setText("Aoga For Happiness");
                        break;
                    case 3:
                        tvSelectProgram.setText("Boga For Happiness");
                        break;
                    case 4:
                        tvSelectProgram.setText("Coga For Happiness");
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class StatsOrActionsModel {
        private int intIcon;
        private String strLabel;
        private String strCount;
        private String strItemType;

        public StatsOrActionsModel(int intIcon, String strLabel, String strItemType) {
            this.intIcon = intIcon;
            this.strLabel = strLabel;
            this.strItemType = strItemType;
        }

        public StatsOrActionsModel(int intIcon, String strLabel, String strCount, String strItemType) {
            this.intIcon = intIcon;
            this.strLabel = strLabel;
            this.strCount = strCount;
            this.strItemType = strItemType;
        }

        public int getIntIcon() {
            return intIcon;
        }

        public void setIntIcon(int intIcon) {
            this.intIcon = intIcon;
        }

        public String getStrLabel() {
            return strLabel;
        }

        public void setStrLabel(String strLabel) {
            this.strLabel = strLabel;
        }

        public String getStrCount() {
            return strCount;
        }

        public void setStrCount(String strCount) {
            this.strCount = strCount;
        }

        public String getStrItemType() {
            return strItemType;
        }

        public void setStrItemType(String strItemType) {
            this.strItemType = strItemType;
        }
    }
}
