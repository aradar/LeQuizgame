package de.spitak.amazinggame.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import de.spitak.amazinggame.util.Display;

/**
 * Created by rschlett on 10/24/16.
 */

public class SwipeableCardView extends CardView {
    private final GestureDetector gestureDetector;
    private final DisplayMetrics displayMetrics;
    private final float SWIPE_X_PERCENTAGE_NEEDED = 1 / 3f;
    private float lastCardX = 0;
    private boolean ignoreScrollEvent;
    private List<OnSwipeListener> swipeListeners;

    {
        gestureDetector = new GestureDetector(getContext(), new SwipeGestureListener());
        displayMetrics = Display.getDisplayMetrics(getContext());
        swipeListeners = new ArrayList<>();
    }

    public SwipeableCardView(Context context) {
        super(context);
    }

    public SwipeableCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeableCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start() {
        //// TODO: 10/24/16 implement activation functionality
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            handleScroll(event, null);
        }

        return gestureDetector.onTouchEvent(event);
    }

    public void setOnSwipeListener(OnSwipeListener l) {
        swipeListeners.add(l);
    }

    private void onSwipe(SwipeDirection direction) {
        for (OnSwipeListener listener : swipeListeners) {
            listener.OnSwipeEvent(new OnSwipeListener.SwipeEvent(direction));
        }
    }

    private void handleScroll(MotionEvent e1, MotionEvent e2) {
        //DEBUG System.out.println("x: " + getX() + "1/3 Display " + (displayMetrics.widthPixels * 0.3));

        if (!ignoreScrollEvent) {
            if (e1.getAction() == MotionEvent.ACTION_DOWN) {
                SwipeableCardView.this.setX(e2.getRawX() - e1.getRawX() - lastCardX);
            }

            if (e2 != null) {
                if (e2.getAction() == MotionEvent.ACTION_MOVE) {
                    SwipeableCardView.this.setX(e2.getRawX() - e1.getRawX());
                    lastCardX = SwipeableCardView.this.getX();
                }
            }

            if (e1.getAction() == MotionEvent.ACTION_UP) {
                float offset = displayMetrics.widthPixels / 2f - getWidth() / 2f;

                //right swipe
                if (getX() > (displayMetrics.widthPixels * SWIPE_X_PERCENTAGE_NEEDED)) {
                    ignoreScrollEvent = true;
                    animate().rotationBy(30).xBy(1000).yBy(300).start();
                    onSwipe(SwipeDirection.RIGHT);
                }
                //left swipe
                else if (Math.abs(getX()) > (displayMetrics.widthPixels * SWIPE_X_PERCENTAGE_NEEDED)) {
                    ignoreScrollEvent = true;
                    onSwipe(SwipeDirection.LEFT);
                    animate().rotationBy(-30).xBy(-1000).yBy(300).start();
                }
                //abort the ship
                else {
                    animate().x(offset).rotation(0).start();
                }
            }
        }
    }

    public enum SwipeDirection {
        LEFT, RIGHT
    }

    public interface OnSwipeListener {
        void OnSwipeEvent(SwipeEvent swipeEvent);

        class SwipeEvent {
            private SwipeableCardView.SwipeDirection swipeDirection;

            public SwipeEvent(SwipeableCardView.SwipeDirection swipeDirection) {
                this.swipeDirection = swipeDirection;
            }

            public SwipeableCardView.SwipeDirection getSwipeDirection() {
                return swipeDirection;
            }
        }
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            handleScroll(e1, e2);

            return true;
        }

    }
}
