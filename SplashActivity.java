package com.jerry94.w3villaquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.q42.android.scrollingimageview.ScrollingImageView;

import java.util.Random;

/**
 * Created by GARVIT on 13-07-2017.
 */
public class SplashActivity extends Activity {

    Thread splashTread;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

//        ScrollingImageView imageView = (ScrollingImageView)findViewById(R.id.splash);

//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        int[] ids = new int[]{R.color.colorAccent,R.color.colorPrimary, R.color.colorPrimaryDark};
//        Random randomGenerator = new Random();
//        int r= randomGenerator.nextInt(ids.length);
//        this.imageView.setImageDrawable(getResources().getDrawable(ids[r]));

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;

                    while (waited < 5000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this,
                            LevelActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();
    }
}
