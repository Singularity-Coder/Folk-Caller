package com.singularitycoder.folkcaller.rankings;

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

import com.singularitycoder.folkcaller.R;

import java.util.ArrayList;

public class RankingsActivity extends AppCompatActivity {

    Toolbar toolbar;

    RankingsAdapter mRankingsAdapter;
    ArrayList<RankingsModel> rankingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpStatusBar();
        setContentView(R.layout.activity_rankings);
        initToolBar();

        RecyclerView recyclerView = findViewById(R.id.recycler_rankings);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        rankingsList = new ArrayList<>();
        rankingsList.add(new RankingsModel(R.drawable.face1, "Maki Sawano", "1", "6453"));
        rankingsList.add(new RankingsModel(R.drawable.face2, "Kizuna Kamika", "2", "5453"));
        rankingsList.add(new RankingsModel(R.drawable.face3, "Allora Mayers", "3", "4453"));
        rankingsList.add(new RankingsModel(R.drawable.face1, "Maki Sawano", "4", "3453"));
        rankingsList.add(new RankingsModel(R.drawable.face2, "Kizuna Kamika", "5", "2453"));
        rankingsList.add(new RankingsModel(R.drawable.face3, "Allora Mayers", "6", "1453"));
        rankingsList.add(new RankingsModel(R.drawable.face1, "Maki Sawano", "7", "953"));
        rankingsList.add(new RankingsModel(R.drawable.face2, "Kizuna Kamika", "8", "853"));
        rankingsList.add(new RankingsModel(R.drawable.face3, "Allora Mayers", "9", "753"));
        rankingsList.add(new RankingsModel(R.drawable.face1, "Maki Sawano", "10", "653"));
        rankingsList.add(new RankingsModel(R.drawable.face2, "Kizuna Kamika", "11", "553"));
        rankingsList.add(new RankingsModel(R.drawable.face3, "Allora Mayers", "12", "453"));
        rankingsList.add(new RankingsModel(R.drawable.face1, "Maki Sawano", "13", "353"));
        rankingsList.add(new RankingsModel(R.drawable.face2, "Kizuna Kamika", "14", "303"));
        rankingsList.add(new RankingsModel(R.drawable.face3, "Allora Mayers", "15", "253"));
        rankingsList.add(new RankingsModel(R.drawable.face1, "Maki Sawano", "16", "213"));
        rankingsList.add(new RankingsModel(R.drawable.face2, "Kizuna Kamika", "17", "203"));
        rankingsList.add(new RankingsModel(R.drawable.face3, "Allora Mayers", "18", "200"));
        rankingsList.add(new RankingsModel(R.drawable.face1, "Maki Sawano", "19", "199"));
        rankingsList.add(new RankingsModel(R.drawable.face2, "Kizuna Kamika", "20", "190"));
        rankingsList.add(new RankingsModel(R.drawable.face3, "Allora Mayers", "21", "150"));


        mRankingsAdapter = new RankingsAdapter(rankingsList, this);
        mRankingsAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mRankingsAdapter);
        mRankingsAdapter.notifyDataSetChanged();
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
        toolbar = findViewById(R.id.toolbar_rank);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Caller Ranks");
        // For back navigation button use this
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
