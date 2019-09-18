package com.singularitycoder.folkcaller.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.singularitycoder.folkcaller.Helper;
import com.singularitycoder.folkcaller.R;
import com.singularitycoder.folkcaller.profileview.ProfileView;
import com.singularitycoder.folkcaller.rankings.RankingsActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    Context mContext;
    FloatingActionButton fab1;

    static ArrayList<PersonModel> adminList;
    static ArrayList<PersonModel> contactList;
    static ArrayList<PersonModel> callerList;
    static AdapterContactCallerAdminChatNotif sAdapterContactCallerAdminChatNotif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpStatusBar();
        setContentView(R.layout.activity_home);
        initToolBar();
        initViewPager();
        initTabLayout();
        mContext = this.getApplicationContext();

        fab1 = findViewById(R.id.floating_button);

    }

    private void setUpStatusBar() {
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
        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("FOLK Caller");
        // For back navigation button use this
        // if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewPager() {
        final int DASHBOARD = 0;
        final int CONTACTS = 1;
        final int CALLERS = 2;
        final int ADMINS = 3;
        final int MY_TASKS = 4;
        final int ASSIGNED_TASKS = 5;
        final int CALL_HISTORY = 6;
        final int SMS_HISTORY = 7;

        viewPager = findViewById(R.id.viewpager_home);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case DASHBOARD:
                        fab1.hide();
                        break;
                    case CONTACTS:
                        fab1.hide();
                        fab1.show();
                        fab1.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_black_24dp));
                        fab1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Upload or Download Contacts", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case CALLERS:
                        fab1.hide();
                        fab1.show();
                        fab1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_add_black_24dp));
                        fab1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Add Caller", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case ADMINS:
                        fab1.hide();
                        fab1.show();
                        fab1.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_add_black_24dp));
                        fab1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Add Admin", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case MY_TASKS:
                        fab1.hide();
                        break;
                    case ASSIGNED_TASKS:
                        fab1.hide();
                        fab1.show();
                        fab1.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
                        fab1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Assing a Task", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case CALL_HISTORY:
                        fab1.hide();
                        fab1.show();
                        fab1.setImageDrawable(getResources().getDrawable(R.drawable.ic_dialpad_black_24dp));
                        fab1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Dial a Number", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case SMS_HISTORY:
                        fab1.hide();
                        fab1.show();
                        fab1.setImageDrawable(getResources().getDrawable(R.drawable.ic_sms_black_24dp));
                        fab1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Send SMS", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }
        });
    }

    private void initTabLayout() {
        tabLayout = findViewById(R.id.tabs_home);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        new Helper().toast("1", getApplicationContext(), 0);
                        break;
                    case 1:
                        new Helper().toast("2", getApplicationContext(), 0);
                        break;
                    case 2:
                        new Helper().toast("3", getApplicationContext(), 0);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        // All of them will have their own respective actions for uploading n downloading etc.
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // adapter.addFrag(new AdminFragment(ContextCompat.getColor(this, R.color.bg_light_grey)), "CHATS");
        // adapter.addFrag(new AdminFragment(ContextCompat.getColor(this, R.color.bg_light_grey)), "NOTIFICATIONS");     // they must be visible
        adapter.addFrag(new DashboardFragment(), "DASHBOARD");
        adapter.addFrag(new ContactFragment(), "CONTACTS");
        adapter.addFrag(new CallerFragment(), "CALLERS");
        adapter.addFrag(new AdminFragment(), "ADMINS");
        adapter.addFrag(new AdminFragment(), "MY TASKS"); // For caller
        adapter.addFrag(new AdminFragment(), "ASSIGNED TASKS");
        adapter.addFrag(new AdminFragment(), "CALL HISTORY");
        adapter.addFrag(new SmsFragment(), "SMS HISTORY");
        viewPager.setAdapter(adapter);
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            // Admin Menu
            case R.id.action_admin_search:
                return true;
            case R.id.action_admin_filter:
                adminFilterDialog(this);
                return true;

            // Caller Menu
            case R.id.action_caller_search:
                return true;
            case R.id.action_caller_filter:
                callerFilterDialog(this);
                return true;

            // Contact Menu
            case R.id.action_contact_search:
                return true;
            case R.id.action__contact_filter:
                contactFilterDialog(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void aboutDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_about);

        Rect displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), dialog.getWindow().getAttributes().height);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tvContactUs = dialog.findViewById(R.id.tv_contact_us);
        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "name@emailaddress.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Us");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Feedback, Help, Report Bugs etc.");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        TextView tvRateUs = dialog.findViewById(R.id.tv_rate_us);
        tvRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=org.srilaprabhupadalila&hl=en")));
            }
        });
        TextView tvVolunteer = dialog.findViewById(R.id.tv_volunteer_appdev);
        tvVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/WCBV2q4b1ZBgDf3B9")));
            }
        });
        TextView tvDedicated = dialog.findViewById(R.id.tv_dedicated_to);
        tvDedicated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.srilaprabhupadalila.org/who-is-srila-prabhupada")));
            }
        });
        TextView tvShareApk = dialog.findViewById(R.id.tv_share_app_apk);
        tvShareApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        TextView tvShareLink = dialog.findViewById(R.id.tv_share_app_link);
        tvShareLink.setOnClickListener(new View.OnClickListener() {
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
        dialog.show();
    }

    public void contactFilterDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_filter_contacts);

        Rect displayRectangle = new Rect();
        Window window = this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), dialog.getWindow().getAttributes().height);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

    public void callerFilterDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_filter_callers);

        Rect displayRectangle = new Rect();
        Window window = this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), dialog.getWindow().getAttributes().height);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

    public void adminFilterDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_filter_admins);

        Rect displayRectangle = new Rect();
        Window window = this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), dialog.getWindow().getAttributes().height);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

    /**
     * Get activity instance from desired context.
     */
    public static Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper)
            return getActivity(((ContextWrapper) context).getBaseContext());
        return null;
    }

    public void showQuickInfoDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_quick_profile);

        Rect displayRectangle = new Rect();
        Window window = ((Activity) context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), dialog.getWindow().getAttributes().height);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imgProfilePic = dialog.findViewById(R.id.img_profile_image);
        imgProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.dismiss();
            }
        });

        ImageView imgCall = dialog.findViewById(R.id.img_quick_call);
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "9535509155";
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                callIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(callIntent);
            }
        });

        ImageView imgSms = dialog.findViewById(R.id.img_quick_message);
        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        ImageView imgWhatsApp = dialog.findViewById(R.id.img_quick_whatsapp);
        imgWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        ImageView imgEmail = dialog.findViewById(R.id.img_quick_email);
        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "name@emailaddress.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Follow Up");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Contact, this is telecaller...");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        ImageView imgShare = dialog.findViewById(R.id.img_quick_share);
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.singularitycoder.com");
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(Intent.createChooser(shareIntent, "Share to"));
            }
        });

        dialog.show();
    }

    public void dialogNotifications(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_notifications);

        Rect displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), dialog.getWindow().getAttributes().height);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imgCloseBtn = dialog.findViewById(R.id.img_dialog_close);
        imgCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ArrayList<PersonModel> notificationList = new ArrayList<>();
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));
        notificationList.add(new PersonModel("Michael Marvin", R.drawable.face3, "Call 300 contacts by today! Take extra care of contact 4 and 5! Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/20"));

        LinearLayoutManager commentLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        RecyclerView notificationsRecycler = dialog.findViewById(R.id.dialog_notif_recycler);
        notificationsRecycler.setLayoutManager(commentLayoutManager);
        notificationsRecycler.setHasFixedSize(true);
        notificationsRecycler.setItemViewCacheSize(20);
        notificationsRecycler.setDrawingCacheEnabled(true);
        notificationsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        NotificationAdapter notificationAdapter = new NotificationAdapter(notificationList, this);
        notificationAdapter.setHasStableIds(true);

        notificationsRecycler.setAdapter(notificationAdapter);


        dialog.show();
    }

    public static class DashModel {
        int intDashImage;
        String strDashTitle;
        String strDashCount;
//        String strDashHeaderCount;

        public DashModel(String strDashCount) {
            this.strDashCount = strDashCount;
        }

        public DashModel(int intDashImage, String strDashTitle, String strDashCount) {
            this.intDashImage = intDashImage;
            this.strDashTitle = strDashTitle;
            this.strDashCount = strDashCount;
        }

        public int getIntDashImage() {
            return intDashImage;
        }

        public void setIntDashImage(int intDashImage) {
            this.intDashImage = intDashImage;
        }

        public String getStrDashTitle() {
            return strDashTitle;
        }

        public void setStrDashTitle(String strDashTitle) {
            this.strDashTitle = strDashTitle;
        }

        public String getStrDashCount() {
            return strDashCount;
        }

        public void setStrDashCount(String strDashCount) {
            this.strDashCount = strDashCount;
        }
    }





    public static class DashboardFragment extends Fragment {
        ArrayList<DashModel> dashList;
        DashAdapter sDashAdapter;

        public DashboardFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home, container, false);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_dash);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            dashList = new ArrayList<>();
            dashList.add(new DashModel("300"));
            dashList.add(new DashModel(R.drawable.ic_rank_black_24dp, "This month's target", "6500"));
            dashList.add(new DashModel(R.drawable.ic_tasks_finished_black_24dp, "Tasks finished", "24"));
            dashList.add(new DashModel(R.drawable.ic_pending_black_24dp, "Tasks pending", "3"));
            dashList.add(new DashModel(R.drawable.ic_contact_mail_black_24dp, "Contacts Called", "2404"));
            dashList.add(new DashModel(R.drawable.ic_hourspent_black_24dp, "Hours spent", "54"));
            dashList.add(new DashModel(R.drawable.ic_conversions_black_24dp, "Conversions", "1051"));
            dashList.add(new DashModel(R.drawable.ic_starrank_black_24dp, "Caller Rank", "7"));


            sDashAdapter = new DashAdapter(dashList, getContext());
            sDashAdapter.setHasStableIds(true);
            recyclerView.setAdapter(sDashAdapter);
            sDashAdapter.notifyDataSetChanged();

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_dash, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                // Home Menu
                case R.id.action_notifications:
                    new HomeActivity().dialogNotifications(getActivity());
                    return true;
                case R.id.action_rankings:
                    Intent rankIntent = new Intent(getActivity(), RankingsActivity.class);
                    startActivity(rankIntent);
                    return true;
                case R.id.action_my_profile:
                    Intent profileIntent = new Intent(getActivity(), ProfileView.class);
                    profileIntent.putExtra("openMyProfile", "MYPROFILE");
                    startActivity(profileIntent);
                    return true;
                case R.id.action_about:
                    new HomeActivity().aboutDialog(getActivity());
                    return true;
                case R.id.action_change_password:
                    return true;
                case R.id.action_delete_account:
                    return true;
                case R.id.action_log_out:
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
        }

        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }
    }


    public static class AdminFragment extends Fragment {
        int color;

        public AdminFragment() {
        }

        @SuppressLint("ValidFragment")
        public AdminFragment(int color) {
            this.color = color;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_person, container, false);

            final FrameLayout frameLayout = view.findViewById(R.id.frame_lay_person);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_person);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            adminList = new ArrayList<>();
            adminList.add(new PersonModel(R.drawable.face2, "Gauranga Das", "Position: Folk Guide", "Availability: 9 AM to 3 PM"));
            adminList.add(new PersonModel(R.drawable.face2, "Gauranga Das", "Position: Folk Guide", "Availability: 9 AM to 3 PM"));
            adminList.add(new PersonModel(R.drawable.face2, "Gauranga Das", "Position: Folk Guide", "Availability: 9 AM to 3 PM"));


            sAdapterContactCallerAdminChatNotif = new AdapterContactCallerAdminChatNotif(getContext(), adminList);
            sAdapterContactCallerAdminChatNotif.setHasStableIds(true);
            sAdapterContactCallerAdminChatNotif.setOnItemClickListener(new AdapterContactCallerAdminChatNotif.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getContext(), position + " got clicked", Toast.LENGTH_LONG).show();
                    // Start activity
                    Intent adminIntent = new Intent(getContext(), ProfileView.class);
                    adminIntent.putExtra("openAdmin", "ADMIN");
                    startActivity(adminIntent);
                }
            });
            recyclerView.setAdapter(sAdapterContactCallerAdminChatNotif);

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_admin, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    public static class ContactFragment extends Fragment {
        int color;

        public ContactFragment() {
        }

        @SuppressLint("ValidFragment")
        public ContactFragment(int color) {
            this.color = color;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_person, container, false);

            final FrameLayout frameLayout = view.findViewById(R.id.frame_lay_person);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_person);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            contactList = new ArrayList<>();
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));
            contactList.add(new PersonModel(R.drawable.face1, "Catherine Millers", "Program: Yoga for Happiness", "Registered Date: July 15, 4019 - 10:15 AM"));

            sAdapterContactCallerAdminChatNotif = new AdapterContactCallerAdminChatNotif(getContext(), contactList);
            sAdapterContactCallerAdminChatNotif.setHasStableIds(true);
            sAdapterContactCallerAdminChatNotif.setOnItemClickListener(new AdapterContactCallerAdminChatNotif.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getContext(), position + " got clicked", Toast.LENGTH_LONG).show();
                    // Start activity
                    Intent contactIntent = new Intent(getContext(), ProfileView.class);
                    contactIntent.putExtra("openContact", "CONTACT");
                    startActivity(contactIntent);
                }
            });
            recyclerView.setAdapter(sAdapterContactCallerAdminChatNotif);

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_contacts, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
        }

        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }
    }


    public static class CallerFragment extends Fragment {
        int color;

        public CallerFragment() {
        }

        @SuppressLint("ValidFragment")
        public CallerFragment(int color) {
            this.color = color;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_person, container, false);

            final FrameLayout frameLayout = view.findViewById(R.id.frame_lay_person);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_person);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            callerList = new ArrayList<>();
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));

            sAdapterContactCallerAdminChatNotif = new AdapterContactCallerAdminChatNotif(getContext(), callerList);
            sAdapterContactCallerAdminChatNotif.setHasStableIds(true);
            sAdapterContactCallerAdminChatNotif.setOnItemClickListener(new AdapterContactCallerAdminChatNotif.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getContext(), position + " got clicked", Toast.LENGTH_LONG).show();
                    // Start activity
                    Intent callerIntent = new Intent(getContext(), ProfileView.class);
                    callerIntent.putExtra("openCaller", "CALLER");
                    startActivity(callerIntent);
                }
            });
            recyclerView.setAdapter(sAdapterContactCallerAdminChatNotif);

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_callers, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    public static class MyTasksFragment extends Fragment {
        int color;

        public MyTasksFragment() {
        }

        @SuppressLint("ValidFragment")
        public MyTasksFragment(int color) {
            this.color = color;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_person, container, false);

            final FrameLayout frameLayout = view.findViewById(R.id.frame_lay_person);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_person);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            callerList = new ArrayList<>();
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));

            sAdapterContactCallerAdminChatNotif = new AdapterContactCallerAdminChatNotif(getContext(), callerList);
            sAdapterContactCallerAdminChatNotif.setHasStableIds(true);
            sAdapterContactCallerAdminChatNotif.setOnItemClickListener(new AdapterContactCallerAdminChatNotif.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getContext(), position + " got clicked", Toast.LENGTH_LONG).show();
                    // Start activity
                    Intent callerIntent = new Intent(getContext(), ProfileView.class);
                    callerIntent.putExtra("openCaller", "CALLER");
                    startActivity(callerIntent);
                }
            });
            recyclerView.setAdapter(sAdapterContactCallerAdminChatNotif);

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_callers, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    public static class AssignedTasksFragment extends Fragment {
        int color;

        public AssignedTasksFragment() {
        }

        @SuppressLint("ValidFragment")
        public AssignedTasksFragment(int color) {
            this.color = color;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_person, container, false);

            final FrameLayout frameLayout = view.findViewById(R.id.frame_lay_person);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_person);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            callerList = new ArrayList<>();
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));

            sAdapterContactCallerAdminChatNotif = new AdapterContactCallerAdminChatNotif(getContext(), callerList);
            sAdapterContactCallerAdminChatNotif.setHasStableIds(true);
            sAdapterContactCallerAdminChatNotif.setOnItemClickListener(new AdapterContactCallerAdminChatNotif.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getContext(), position + " got clicked", Toast.LENGTH_LONG).show();
                    // Start activity
                    Intent callerIntent = new Intent(getContext(), ProfileView.class);
                    callerIntent.putExtra("openCaller", "CALLER");
                    startActivity(callerIntent);
                }
            });
            recyclerView.setAdapter(sAdapterContactCallerAdminChatNotif);

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_callers, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    public static class CallHistoryFragment extends Fragment {
        int color;

        public CallHistoryFragment() {
        }

        @SuppressLint("ValidFragment")
        public CallHistoryFragment(int color) {
            this.color = color;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_person, container, false);

            final FrameLayout frameLayout = view.findViewById(R.id.frame_lay_person);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_person);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            callerList = new ArrayList<>();
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));
            callerList.add(new PersonModel(R.drawable.face3, "Miki Mathews", "Position: Folk Member", "Availability: 9 AM to 3 PM"));

            sAdapterContactCallerAdminChatNotif = new AdapterContactCallerAdminChatNotif(getContext(), callerList);
            sAdapterContactCallerAdminChatNotif.setHasStableIds(true);
            sAdapterContactCallerAdminChatNotif.setOnItemClickListener(new AdapterContactCallerAdminChatNotif.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getContext(), position + " got clicked", Toast.LENGTH_LONG).show();
                    // Start activity
                    Intent callerIntent = new Intent(getContext(), ProfileView.class);
                    callerIntent.putExtra("openCaller", "CALLER");
                    startActivity(callerIntent);
                }
            });
            recyclerView.setAdapter(sAdapterContactCallerAdminChatNotif);

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_callers, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    public static class SmsFragment extends Fragment {
        ArrayList<PersonModel> smsList;
        SmsAdapter smsAdapter;

        public SmsFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_person, container, false);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_person);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            smsList = new ArrayList<>();
            smsList.add(new PersonModel("Jasmin Jamon", R.drawable.face3, "Hey Jasmin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/19", "3"));
            smsList.add(new PersonModel("Marvin Michael", R.drawable.face2, "Hey Marvin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "21/6/20", "7"));
            smsList.add(new PersonModel("Juniper Jupiter", R.drawable.face1, "Hey Juniper, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "7/2/20", "1"));
            smsList.add(new PersonModel("Jasmin Jamon", R.drawable.face3, "Hey Jasmin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/19", "3"));
            smsList.add(new PersonModel("Marvin Michael", R.drawable.face2, "Hey Marvin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "21/6/20", "7"));
            smsList.add(new PersonModel("Juniper Jupiter", R.drawable.face1, "Hey Juniper, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "7/2/20", "1"));
            smsList.add(new PersonModel("Jasmin Jamon", R.drawable.face3, "Hey Jasmin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/19", "3"));
            smsList.add(new PersonModel("Marvin Michael", R.drawable.face2, "Hey Marvin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "21/6/20", "7"));
            smsList.add(new PersonModel("Juniper Jupiter", R.drawable.face1, "Hey Juniper, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "7/2/20", "1"));
            smsList.add(new PersonModel("Jasmin Jamon", R.drawable.face3, "Hey Jasmin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "19/2/19", "3"));
            smsList.add(new PersonModel("Marvin Michael", R.drawable.face2, "Hey Marvin, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "21/6/20", "7"));
            smsList.add(new PersonModel("Juniper Jupiter", R.drawable.face1, "Hey Juniper, what happened to the 2 million contacts i told u to call in 3 minutes? Huhahahahah huahahaha huahahahahahahahaaaaaaaaa!", "7/2/20", "1"));



            smsAdapter = new SmsAdapter(smsList, getContext());
            smsAdapter.setHasStableIds(true);

            recyclerView.setAdapter(smsAdapter);

            return view;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_callers, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }


}
