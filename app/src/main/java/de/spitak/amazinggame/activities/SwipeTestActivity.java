package de.spitak.amazinggame.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.Utils;
import de.spitak.amazinggame.views.SwipeableCardView;

public class SwipeTestActivity extends AppCompatActivity {

    RelativeLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_test);

        layout = (RelativeLayout) findViewById(R.id.activity_swipe_test);
        SwipeableCardView card = getSwipeableCardView(layout);
        layout.addView(card);
    }

    @NonNull
    private SwipeableCardView getSwipeableCardView(final RelativeLayout layout) {
        SwipeableCardView card = new SwipeableCardView(layout.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        DisplayMetrics displayMetrics = Utils.getDisplayMetrics(this);
        final int DEFAULT_MARGIN = Utils.dpToPx(displayMetrics, 15);
        final int BOTTOM_MARGIN = Utils.dpToPx(displayMetrics, 120);
        layoutParams.setMargins(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, BOTTOM_MARGIN);
        card.setLayoutParams(layoutParams);
        card.setOnSwipeListener(new SwipeableCardView.OnSwipeListener() {
            @Override
            public void OnSwipeEvent(SwipeEvent swipeEvent) {
                // TODO: 10/24/16 removeAllViews() seems to cancel all animations and just addView() does not show the new view
                //layout.removeAllViews();
                SwipeableCardView card = getSwipeableCardView(layout);
                card.setAlpha(0);
                layout.addView(card);
                card.animate().alpha(1).start();
            }
        });
        return card;
    }
}

