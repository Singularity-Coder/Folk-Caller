package com.singularitycoder.folkcaller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    static ArrayList<ItemContactCallerAdminChatNotif> adminList;
    static AdapterContactCallerAdminChatNotif sAdapterContactCallerAdminChatNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initToolBar();
        initViewPager();
        initTabLayout();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Folk Caller");
        // For back navigation button use this
        // if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewpager_home);
        setupViewPager(viewPager);
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
                        toast("1");
                        break;
                    case 1:
                        toast("2");
                        break;
                    case 2:
                        toast("3");
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

    void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AdminFragment(ContextCompat.getColor(this, R.color.bg_light_grey)), "CONTACTS");
        adapter.addFrag(new AdminFragment(ContextCompat.getColor(this, R.color.bg_light_grey)), "CALLERS");
        adapter.addFrag(new AdminFragment(ContextCompat.getColor(this, R.color.bg_light_grey)), "ADMINS");
        adapter.addFrag(new AdminFragment(ContextCompat.getColor(this, R.color.bg_light_grey)), "CHATS");
        adapter.addFrag(new AdminFragment(ContextCompat.getColor(this, R.color.bg_light_grey)), "NOTIFICATIONS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        // Find the menuItem to add your SubMenu
        MenuItem myMenuItem = menu.findItem(R.id.action_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_admin_search:

                return true;
            case R.id.action_admin_filter:
                contactFilterDialog(this);
                return true;
            case R.id.action_admin_add:

                return true;
            case R.id.action_bulk_sms:
                startActivity(new Intent(HomeActivity.this, BulkSmsActivity.class));
                return true;
            case R.id.action_about:
                aboutDialog(this);
                return true;
            case R.id.action_my_profile:

                return true;
            case R.id.action_settings:

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
        Window window = this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), dialog.getWindow().getAttributes().height);

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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/forms/about/")));
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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=org.srilaprabhupadalila&hl=en")));
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

        dialog.show();
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

        ImageView imgProfilePic = dialog.findViewById(R.id.img_profile_image);
        imgProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ImageView imgWhatsApp = dialog.findViewById(R.id.img_quick_whatsapp);
        imgWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ImageView imgSms = dialog.findViewById(R.id.img_quick_message);
        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ImageView imgCall = dialog.findViewById(R.id.img_quick_call);
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ImageView imgInfo = dialog.findViewById(R.id.img_quick_info);
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_admin, container, false);

            final FrameLayout frameLayout = view.findViewById(R.id.fragment_home_admin);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = view.findViewById(R.id.recycler_home_admin);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

            adminList = new ArrayList<>();
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));
            adminList.add(new ItemContactCallerAdminChatNotif(R.drawable.face3, "Gauranga Das", "Folk Guide", "9 AM to 3 PM"));

            sAdapterContactCallerAdminChatNotif = new AdapterContactCallerAdminChatNotif(getContext(), adminList);
            sAdapterContactCallerAdminChatNotif.setHasStableIds(true);
            sAdapterContactCallerAdminChatNotif.setOnItemClickListener(new AdapterContactCallerAdminChatNotif.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getContext(), position + " got clicked", Toast.LENGTH_LONG).show();
                    // Start activity
                    startActivity(new Intent(getContext(), DetailViewContactCallerAdmin.class));
                }
            });
            recyclerView.setAdapter(sAdapterContactCallerAdminChatNotif);

            return view;
        }
    }

}
