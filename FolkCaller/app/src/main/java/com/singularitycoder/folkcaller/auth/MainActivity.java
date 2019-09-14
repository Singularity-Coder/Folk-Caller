package com.singularitycoder.folkcaller.auth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.singularitycoder.folkcaller.home.HomeActivity;
import com.singularitycoder.folkcaller.R;

import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CoordinatorLayout mCoordinatorLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ImageView headerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatuBarColor();
        setContentView(R.layout.activity_main);
        inits();
        setUpViewPager();
        setUpTabLayout();
        setUpToolbar();
        setUpAppbarLayout();
        setUpCollapsingToolbar();
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


    private void inits() {
        mCoordinatorLayout = findViewById(R.id.coordinator_main);
        headerImage = findViewById(R.id.img_main_header);
        headerImage.setImageDrawable(getResources().getDrawable( R.drawable.blink));
        AnimationDrawable frameAnimation = (AnimationDrawable) headerImage.getDrawable();
        frameAnimation.start();
    }


    private void setUpViewPager() {
        // Set ViewPager
        viewPager = findViewById(R.id.viewpager_main);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new SignUpFragment(ContextCompat.getColor(this, R.color.colorWhite)), "SIGN UP");
        adapter.addFrag(new LoginFragment(ContextCompat.getColor(this, R.color.colorWhite)), "LOGIN");
        viewPager.setAdapter(adapter);
    }


    private void setUpTabLayout() {
        // Set TabLayout
        tabLayout = findViewById(R.id.tablayout_main);
        tabLayout.setupWithViewPager(viewPager);

        // Do something on selecting each tab of tab layout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                Log.d(TAG, "onTabSelected: pos: " + tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "You clciked this 1", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "You clciked this 2", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Snackbar.make(mCoordinatorLayout, "1 got away", Snackbar.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Snackbar.make(mCoordinatorLayout, "2 got away", Snackbar.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "You clciked 1 again", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "You clciked 2 again", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }


    private void setUpToolbar() {
        // Set Toolbar
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Folk Caller");
    }


    private void setUpAppbarLayout() {
        AppBarLayout appBarLayout = findViewById(R.id.appbar_main);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    if (getSupportActionBar() != null)
                        getSupportActionBar().setTitle("FOLK Caller");
                    isShow = true;
                } else if (isShow) {
                    if (getSupportActionBar() != null) getSupportActionBar().setTitle(" ");
                    isShow = false;
                }
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    //  Collapsed
//                    Toast.makeText(getApplicationContext(), "Collapsed", Toast.LENGTH_LONG).show();
//                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
//                    tabLayout.setTabTextColors(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorBlack));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
                } else {
                    //Expanded
//                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));
//                    tabLayout.setTabTextColors(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorWhite));
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    tabLayout.setBackgroundColor(Color.TRANSPARENT);
//                    toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
    }


    private void setUpCollapsingToolbar() {
        // Set CollapsingToolbar
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_main);

        // Set color of CollaspongToolbar when collapsing
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {
//                    int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
//                    int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimaryDark);
                    collapsingToolbarLayout.setContentScrimColor(R.color.colorPrimary);
                    collapsingToolbarLayout.setStatusBarScrimColor(R.color.colorTransparent);
                }
            });
        } catch (Exception e) {
            // if Bitmap fetch fails, fallback to primary colors
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));
            collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
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


    public static class LoginFragment extends Fragment {
        int color;
        Button btnUseFingerPrint;
        TextView tvUsePasswordToLogin;
        TextView tvLoginMemberType;
        TextView tvNotAMember;
        TextView tvFolkIdLogin;
        EditText etFolkIdLogin;
        TextView tvAdminNumber;
        EditText etAdminNumber;

        public LoginFragment() {
        }

        @SuppressLint("ValidFragment")
        public LoginFragment(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_login, container, false);

            Button btnLogin = view.findViewById(R.id.btn_create_event_invite_people);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
            });

            tvLoginMemberType = view.findViewById(R.id.et_login_member_type);
            tvLoginMemberType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogSignUpMemberType();
                }
            });

            tvFolkIdLogin = view.findViewById(R.id.tv_login_folkid);
            etFolkIdLogin = view.findViewById(R.id.et_login_folkid);
            tvAdminNumber = view.findViewById(R.id.tv_login_admin_number);
            etAdminNumber = view.findViewById(R.id.et_login_admin_number);


            btnUseFingerPrint = view.findViewById(R.id.et_login_finger_print);
            btnUseFingerPrint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fingerPrintDialogFunc(getActivity());
                }
            });
            tvNotAMember = view.findViewById(R.id.tv_login_create_account);
            tvNotAMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    new MainActivity().tabLayout.getTabAt(0);
                }
            });

            return view;
        }

        public void dialogSignUpMemberType() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("I am a");
            String[] selectArray = {"Folk Member", "Admin"};
            builder.setItems(selectArray, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            tvLoginMemberType.setText("Folk Member");

                            tvFolkIdLogin.setVisibility(View.VISIBLE);
                            etFolkIdLogin.setVisibility(View.VISIBLE);

                            tvAdminNumber.setVisibility(View.GONE);
                            etAdminNumber.setVisibility(View.GONE);
                            break;
                        case 1:
                            tvLoginMemberType.setText("Admin");

                            tvAdminNumber.setVisibility(View.VISIBLE);
                            etAdminNumber.setVisibility(View.VISIBLE);

                            tvFolkIdLogin.setVisibility(View.GONE);
                            etFolkIdLogin.setVisibility(View.GONE);
                            break;
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        public void fingerPrintDialogFunc(Activity activity) {
            final Dialog fingerPrintDialog = new Dialog(activity);
            fingerPrintDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            fingerPrintDialog.setCancelable(true);
            fingerPrintDialog.setContentView(R.layout.dialog_finger_print);

            Rect displayRectangle = new Rect();
            Window window = activity.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            fingerPrintDialog.getWindow().setLayout((int) (displayRectangle.width() * 0.8f), fingerPrintDialog.getWindow().getAttributes().height);

            fingerPrintDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            tvUsePasswordToLogin = fingerPrintDialog.findViewById(R.id.tv_use_password_login);
            tvUsePasswordToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fingerPrintDialog.dismiss();
                }
            });

            fingerPrintDialog.show();
        }
    }

    public static class SignUpFragment extends Fragment {
        int color;
        TextView tvTermsPrivacy;
        TextView tvMemberType;
        TextView tvFolkIdLogin;
        EditText etFolkIdLogin;
        TextView tvAdminNumber;
        EditText etAdminNumber;

        public SignUpFragment() {
        }

        @SuppressLint("ValidFragment")
        public SignUpFragment(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_signup, container, false);

            tvTermsPrivacy = view.findViewById(R.id.tv_signup_terms);
            tvTermsPrivacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iskconbangalore.org/privacy-policy/")));
                }
            });

            tvMemberType = view.findViewById(R.id.et_signup_member_type);
            tvMemberType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogSignUpMemberType();
                }
            });

            tvFolkIdLogin = view.findViewById(R.id.tv_login_folkid);
            etFolkIdLogin = view.findViewById(R.id.et_login_folkid);
            tvAdminNumber = view.findViewById(R.id.tv_signup_admin_number);
            etAdminNumber = view.findViewById(R.id.et_signup_admin_number);

            return view;
        }

        public void dialogSignUpMemberType() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("I am a");
            String[] selectArray = {"Folk Member", "Admin"};
            builder.setItems(selectArray, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            tvMemberType.setText("Folk Member");

                            tvFolkIdLogin.setVisibility(View.VISIBLE);
                            etFolkIdLogin.setVisibility(View.VISIBLE);

                            tvAdminNumber.setVisibility(View.GONE);
                            etAdminNumber.setVisibility(View.GONE);
                            break;
                        case 1:
                            tvMemberType.setText("Admin");

                            tvAdminNumber.setVisibility(View.VISIBLE);
                            etAdminNumber.setVisibility(View.VISIBLE);

                            tvFolkIdLogin.setVisibility(View.GONE);
                            etFolkIdLogin.setVisibility(View.GONE);
                            break;
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}