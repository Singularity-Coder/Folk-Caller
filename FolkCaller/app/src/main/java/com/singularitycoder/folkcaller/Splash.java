package com.singularitycoder.folkcaller;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.singularitycoder.folkcaller.auth.MainActivity;

public class Splash extends AppCompatActivity {

    private View bgSplash;
    private ImageView appIcon;
    private ConstraintLayout mConstraintLayout;
    private AnimationDrawable mAnimationDrawable;
    private final int SHOW_SPLASH_FOR = 2000;   // milli seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_splash);

        instanstiations();
        animateGradientBackground();
        animateAppIcon();
        delaySplashFor2Sec();
    }

    private void instanstiations() {
        bgSplash = findViewById(R.id.bg_splash);
        appIcon = findViewById(R.id.iv_logo_splash_screen);
        mConstraintLayout = findViewById(R.id.con_lay_splash);
    }

    private void animateGradientBackground() {
        mAnimationDrawable = (AnimationDrawable) bgSplash.getBackground();
        mAnimationDrawable.setEnterFadeDuration(1000);
        mAnimationDrawable.setExitFadeDuration(1000);
    }

    private void animateAppIcon() {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(appIcon, "alpha", 0.0F, 1.0F);
        alphaAnimation.setStartDelay(600);
        alphaAnimation.setDuration(1000);
        alphaAnimation.start();
        appIcon.setVisibility(View.VISIBLE);
    }

    private void delaySplashFor2Sec() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SHOW_SPLASH_FOR);
    }

    private void startZoomInAnimation() {
        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_animation);
        bgSplash.startAnimation(animZoomIn);
    }

    private void startZoomOutAnimation() {
        Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out_animation);
        bgSplash.startAnimation(animZoomOut);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimationDrawable.start();
    }
}
