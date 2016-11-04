package de.spitak.amazinggame.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import de.spitak.amazinggame.util.Display;
import de.spitak.amazinggame.util.SwipeAnimationHelper;
import de.spitak.amazinggame.util.SwipeDetector;

/**
 * Created by rschlett on 10/24/16.
 */

public class SwipeableCardView extends CardView {
    private final DisplayMetrics displayMetrics;
    private SwipeDetector swipeDetector;
    private SwipeAnimationHelper animationHelper;
    private OnSwipeListener onSwipeListener;

    {
        displayMetrics = Display.getDisplayMetrics(getContext());
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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (swipeDetector == null) {
            swipeDetector = new SwipeDetector(getContext(), left, top);
            swipeDetector.setOnSwipeListener(new SwipeListener());
        }
        if (animationHelper == null) {
            animationHelper = new SwipeAnimationHelper(this, left, top);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (swipeDetector != null) {
            return swipeDetector.onTouchEvent(event);
        }

        return false;
    }

    public void setOnSwipeListener(OnSwipeListener l) {
        this.onSwipeListener = l;
    }

    public interface OnSwipeListener {

        void onSwipe(SwipeEvent swipeEvent);
        class SwipeEvent {

            private SwipeDetector.SwipeDirection swipeDirection;

            public SwipeEvent(SwipeDetector.SwipeDirection swipeDirection) {
                this.swipeDirection = swipeDirection;
            }

            public SwipeDetector.SwipeDirection getSwipeDirection() {
                return swipeDirection;
            }

        }
    }

    private class SwipeListener implements SwipeDetector.OnSwipeListener {

        @Override
        public void onSwipe(final SwipeEvent swipeEvent) {
            if (swipeEvent.getSwipeDirection() != SwipeDetector.SwipeDirection.NONE) {
                animationHelper.swipeOut(swipeEvent.getSwipeDirection(), new Runnable() {
                    @Override
                    public void run() {
                        swipeDetector.resetCoordinates();
                        onSwipeListener.onSwipe(new OnSwipeListener.SwipeEvent(swipeEvent.getSwipeDirection()));
                        animationHelper.bottomSwipeIn(null);
                    }
                });
            } else {
                swipeDetector.resetCoordinates();
                animationHelper.resetToCenter(null);
            }
        }

        @Override
        public void onSwipePossible(SwipeEvent swipeEvent) {
        }

        @Override
        public void onPositionUpdate(float rawX, float rawY) {
            setX(rawX);
            setY(rawY);
        }
    }

}
