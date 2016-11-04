package de.spitak.amazinggame.util;

import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by rschlett on 11/1/16.
 */

public class SwipeAnimationHelper {
    private final View view;

    private final float RESET_RAW_X;
    private final float RESET_RAW_Y;
    private final float HORIZONTAL_X_MOVEMENT;
    private final float HORIZONTAL_Y_MOVEMENT;
    private final float VERTICAL_X_MOVEMENT;
    private final float VERTICAL_Y_MOVEMENT;

    public SwipeAnimationHelper(View view, float x, float y) {
        this.RESET_RAW_X = x;
        this.RESET_RAW_Y = y;
        DisplayMetrics displayMetrics = Display.getDisplayMetrics(view.getContext());
        this.HORIZONTAL_X_MOVEMENT = displayMetrics.widthPixels;
        this.HORIZONTAL_Y_MOVEMENT = displayMetrics.heightPixels / 6;
        this.VERTICAL_X_MOVEMENT = displayMetrics.widthPixels / 6;
        this.VERTICAL_Y_MOVEMENT = displayMetrics.heightPixels;
        this.view = view;
    }

    public void swipeOut(SwipeDetector.SwipeDirection direction, Runnable runnable) {
        switch (direction) {
            case LEFT:
                leftSwipeOut(runnable);
                break;

            case RIGHT:
                rightSwipeOut(runnable);
                break;

            case TOP:
                topSwipeOut(runnable);
                break;

            case BOTTOM:
                bottomSwipeOut(runnable);
                break;

            case NONE:
                resetToCenter(runnable);
                break;

            default:
                throw new IllegalArgumentException(direction + ", is not supported!");
        }
    }

    public void leftSwipeOut(Runnable runnable) {
        startOut(runnable, -1, HORIZONTAL_X_MOVEMENT, HORIZONTAL_Y_MOVEMENT);
    }

    public void rightSwipeOut(Runnable runnable) {
        startOut(runnable, 1, HORIZONTAL_X_MOVEMENT, HORIZONTAL_Y_MOVEMENT);
    }

    public void topSwipeOut(Runnable runnable) {
        startOut(runnable, -1, VERTICAL_X_MOVEMENT, VERTICAL_Y_MOVEMENT);
    }

    public void bottomSwipeOut(Runnable runnable) {
        startOut(runnable, +1, VERTICAL_X_MOVEMENT, VERTICAL_Y_MOVEMENT);
    }

    public void swipeIn(SwipeDetector.SwipeDirection direction, Runnable runnable) {
        switch (direction) {
            case LEFT:
                leftSwipeIn(runnable);
                break;

            case RIGHT:
                rightSwipeIn(runnable);
                break;

            case TOP:
                topSwipeIn(runnable);
                break;

            case BOTTOM:
                bottomSwipeIn(runnable);
                break;

            default:
                throw new IllegalArgumentException("The given direction is not supported!");
        }
    }

    public void leftSwipeIn(Runnable runnable) {
        startIn(runnable, RESET_RAW_X - HORIZONTAL_X_MOVEMENT, RESET_RAW_Y);
    }

    public void rightSwipeIn(Runnable runnable) {
        startIn(runnable, RESET_RAW_X + HORIZONTAL_X_MOVEMENT, RESET_RAW_Y);
    }

    public void topSwipeIn(Runnable runnable) {
        startIn(runnable, RESET_RAW_X, RESET_RAW_Y - VERTICAL_Y_MOVEMENT);
    }

    public void bottomSwipeIn(Runnable runnable) {
        startIn(runnable, RESET_RAW_X, RESET_RAW_Y + VERTICAL_Y_MOVEMENT);
    }

    public void appearInCenter(Runnable runnable) {
        view.setAlpha(0);
        view.setX(RESET_RAW_X);
        view.setY(RESET_RAW_Y);
        view.setRotation(0);

        view.animate()
                .withEndAction(runnable)
                .alpha(1);
    }

    private void startOut(Runnable runnable, int modifier, float xMovement, float yMovement) {
        final int ROTATION = 30;

        view.animate()
                .withEndAction(runnable)
                .rotationBy(modifier * ROTATION)
                .xBy(modifier * xMovement)
                .yBy(yMovement);
    }

    private void startIn(Runnable runnable, float xPostion, float yPosition) {
        view.setX(xPostion);
        view.setY(yPosition);
        view.setRotation(0);

        view.animate()
                .withEndAction(runnable)
                .x(RESET_RAW_X)
                .y(RESET_RAW_Y);
    }

    public void resetToCenter(Runnable runnable) {
        view.animate()
                .withEndAction(runnable)
                .rotation(0)
                .x(RESET_RAW_X)
                .y(RESET_RAW_Y);
    }
}
