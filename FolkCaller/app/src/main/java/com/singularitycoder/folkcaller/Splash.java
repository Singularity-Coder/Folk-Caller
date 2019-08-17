package com.singularitycoder.folkcaller;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.singularitycoder.folkcaller.auth.MainActivity;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    ImageView logo;
    View bgSplash;
    AnimationDrawable mAnimationDrawable;
    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mConstraintLayout = findViewById(R.id.con_lay_splash);
        mAnimationDrawable = (AnimationDrawable) mConstraintLayout.getBackground();
        mAnimationDrawable.setEnterFadeDuration(1000);
        mAnimationDrawable.setExitFadeDuration(1000);

        logo = findViewById(R.id.iv_logo_splash_screen);
//        bgSplash = findViewById(R.id.bg_splash);

        // New Handler to start the Menu-Activity and close this Splash-Screen after some seconds.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent that will start the Menu-Activity.
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

//        startZoomInAnimation();
        animateLogo();
    }

    private void animateLogo() {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(logo, "alpha", 0.0F, 1.0F);
        alphaAnimation.setStartDelay(600);
        alphaAnimation.setDuration(1000);
        alphaAnimation.start();
        logo.setVisibility(View.VISIBLE);
    }

//    public void startZoomInAnimation() {
//        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_animation);
//        bgSplash.startAnimation(animZoomIn);
//    }

    public void startZoomOutAnimation() {
        Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out_animation);
        bgSplash.startAnimation(animZoomOut);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimationDrawable.start();
    }
}
