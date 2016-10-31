package de.spitak.amazinggame.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.util.Display;
import de.spitak.amazinggame.view.SwipeableCardView;

public class SwipeStackTestActivity extends AppCompatActivity {

    RelativeLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_stack_test);

        DisplayMetrics displayMetrics = Display.getDisplayMetrics(this);
        final int Z_DIFFERENCE = Display.dpToPx(displayMetrics, 5);
        final int Y_DIFFERENCE = Display.dpToPx(displayMetrics, 5);

        layout = (RelativeLayout) findViewById(R.id.activity_swipe_stack_test);
        SwipeableCardView card = getSwipeableCardView(layout);
        card.setZ(Z_DIFFERENCE * 3);
        layout.addView(card);
        card = getSwipeableCardView(layout);
        card.setY(Y_DIFFERENCE);
        card.setZ(Z_DIFFERENCE * 2);
        layout.addView(card);
        card = getSwipeableCardView(layout);
        card.setY(Y_DIFFERENCE * 2);
        card.setZ(Z_DIFFERENCE);
        layout.addView(card);
    }

    @NonNull
    private SwipeableCardView getSwipeableCardView(final RelativeLayout layout) {
        SwipeableCardView card = new SwipeableCardView(layout.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        // should be changed to be equal with 15dp
        layoutParams.setMargins(50, 50, 50, 300);
        card.setLayoutParams(layoutParams);
        card.setOnSwipeListener(new SwipeableCardView.OnSwipeListener() {
            @Override
            public void OnSwipeEvent(SwipeEvent swipeEvent) {

            }
        });
        return card;
    }
}
