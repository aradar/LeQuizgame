package de.spitak.amazinggame.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rschlett on 10/31/16.
 */

public class SwipeDetector {
    private final float X_THRESHOLD = 100; // what value???!
    private final float Y_THRESHOLD = 100; // what value???!
    private final int ORIGINAL_VIEW_RAW_X;
    private final int ORIGINAL_VIEW_RAW_Y;
    private final GestureDetector gestureDetector;
    private final List<OnSwipeListener> swipeListeners;
    private final DisplayMetrics displayMetrics;

    private float viewRawX;
    private float viewRawY;

    private boolean downOccurred;
    private float downRawX;
    private float downRawY;
    private float lastRawX;
    private float lastRawY;
    private SwipeDirection direction;

    private boolean onlyXAxis;
    private boolean onlyYAxis;

    public SwipeDetector(Context context, int originalViewRawX,
                         int originalViewRawY) {
        this.gestureDetector = new GestureDetector(context, new SwipeGestureListener());
        this.swipeListeners = new ArrayList<>();
        this.ORIGINAL_VIEW_RAW_X = originalViewRawX;
        this.ORIGINAL_VIEW_RAW_Y = originalViewRawY;
        this.viewRawX = originalViewRawX;
        this.viewRawY = originalViewRawY;
        this.direction = SwipeDirection.NONE;
        this.displayMetrics = Display.getDisplayMetrics(context);
    }

    public void setOnlyXAxis(boolean onlyXAxis) {
        this.onlyXAxis = onlyXAxis;
    }

    public void setOnlyYAxis(boolean onlyYAxis) {
        this.onlyYAxis = onlyYAxis;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        boolean consumed = gestureDetector.onTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            onSwipeStop(ev.getRawX(), ev.getRawY());
            downOccurred = false; // event ended
        }

        return consumed;
    }

    public void resetCoordinates() {
        viewRawX = ORIGINAL_VIEW_RAW_X;
        viewRawY = ORIGINAL_VIEW_RAW_Y;
        downRawX = 0;
        downRawY = 0;
        lastRawX = 0;
        lastRawY = 0;
    }

    public void setOnSwipeListener(OnSwipeListener l) {
        swipeListeners.add(l);
    }

    private void onSwipeStart(float rawX, float rawY) {
        downRawX = rawX;
        downRawY = rawY;
        lastRawX = rawX;
        lastRawY = rawY;
    }

    private void onSwipeMove(float rawX, float rawY) {
        viewRawX -= lastRawX - rawX;
        lastRawX = rawX;
        viewRawY -= lastRawY - rawY;
        lastRawY = rawY;

        if (onlyXAxis) {
            updatePosition(viewRawX, ORIGINAL_VIEW_RAW_Y);
        } else if (onlyYAxis) {
            updatePosition(ORIGINAL_VIEW_RAW_X, viewRawY);
        } else {
            updatePosition(viewRawX, viewRawY);
        }

        SwipeDirection direction = getSwipeDirection(rawX, rawY);
        if (direction != this.direction) {
            this.direction = direction;
            onSwipePossible(direction);
        }
    }

    private void onSwipeStop(float rawX, float rawY) {
        onSwipe(getSwipeDirection(rawX, rawY));
    }

    @NonNull
    private SwipeDirection getSwipeDirection(float rawX, float rawY) {
/*
        float xDiff = downRawX - rawX;
        float yDiff = downRawY - rawY;

        SwipeDirection direction = SwipeDirection.NONE;
        if (Math.abs(xDiff) >= X_THRESHOLD && !onlyYAxis) {
            direction = getHorizontalSwipeDirection(xDiff);
        } else if (Math.abs(yDiff) >= Y_THRESHOLD && !onlyXAxis) {
            direction = getVerticalSwipeDirection(yDiff);
        } else if (Math.abs(xDiff) >= X_THRESHOLD || Math.abs(yDiff) >= Y_THRESHOLD && !onlyYAxis && !onlyXAxis) {
            if (Math.max(Math.abs(xDiff), Math.abs(yDiff)) == Math.abs(xDiff)) {
                direction = getHorizontalSwipeDirection(xDiff);
            } else {
                direction = getVerticalSwipeDirection(yDiff);
            }
        }
*/
        final float PERCENTAGE_NEEDED = 1 / 3f;
        final float X_THRESHOLD = displayMetrics.widthPixels * PERCENTAGE_NEEDED;
        final float Y_THRESHOLD = displayMetrics.heightPixels * PERCENTAGE_NEEDED;

        //right swipe
        if (viewRawX > (X_THRESHOLD)) {
            direction = SwipeDirection.RIGHT;
        }
        //left swipe
        else if (Math.abs(viewRawX) > (X_THRESHOLD)) {
            direction = SwipeDirection.LEFT;
        }
        //abort the ship
        else {
            direction = SwipeDirection.NONE;
        }

        return direction;
    }

    @NonNull
    private SwipeDirection getVerticalSwipeDirection(float yDiff) {
        return getSwipeDirection(yDiff, SwipeDirection.TOP, SwipeDirection.BOTTOM);
    }

    @NonNull
    private SwipeDirection getHorizontalSwipeDirection(float xDiff) {
        return getSwipeDirection(xDiff, SwipeDirection.LEFT, SwipeDirection.RIGHT);
    }

    @NonNull
    private SwipeDirection getSwipeDirection(float diff, SwipeDirection firstOption,
                                             SwipeDirection secondOption) {
        SwipeDirection direction;
        if (diff > 0) {
            direction = firstOption;
        } else {
            direction = secondOption;
        }
        return direction;
    }

    private void onSwipe(SwipeDirection direction) {
        for (OnSwipeListener listener : swipeListeners) {
            listener.onSwipe(new OnSwipeListener.SwipeEvent(direction));
        }
    }

    private void onSwipePossible(SwipeDirection direction) {
        for (OnSwipeListener listener : swipeListeners) {
            listener.onSwipePossible(new OnSwipeListener.SwipeEvent(direction));
        }

    }

    private void updatePosition(float rawX, float rawY) {
        for (OnSwipeListener swipeListener : swipeListeners) {
            swipeListener.onPositionUpdate(rawX, rawY);
        }
    }

    public enum SwipeDirection {
        TOP, LEFT, RIGHT, NONE, BOTTOM
    }

    public interface OnSwipeListener {
        void onSwipe(SwipeEvent swipeEvent);

        void onSwipePossible(SwipeEvent swipeEvent);

        void onPositionUpdate(float rawX, float rawY);

        class SwipeEvent {
            private SwipeDirection swipeDirection;

            public SwipeEvent(SwipeDirection swipeDirection) {
                this.swipeDirection = swipeDirection;
            }

            public SwipeDirection getSwipeDirection() {
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
            if (!downOccurred) {
                downOccurred = true;
                onSwipeStart(e1.getRawX(), e1.getRawY());
            } else {
                onSwipeMove(e2.getRawX(), e2.getRawY());
            }

            return true;
        }

    }
}
