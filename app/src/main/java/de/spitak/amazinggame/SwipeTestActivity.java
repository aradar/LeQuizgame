package de.spitak.amazinggame;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

public class SwipeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_test);
        SwipeableCardView card = (SwipeableCardView) findViewById(R.id.testcard);
        card.setOnSwipeListener(new OnSwipeListener() {
            @Override
            public void OnSwipeEvent(SwipeEvent swipeEvent) {
                Toast.makeText(SwipeTestActivity.this, swipeEvent.getSwipeDirection().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

