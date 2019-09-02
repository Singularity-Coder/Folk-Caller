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
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));
        rankingsList.add(new RankingsModel("1", R.drawable.face1, "Maki Sawano", "6453"));


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
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Rankings");
        // For back navigation button use this
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
