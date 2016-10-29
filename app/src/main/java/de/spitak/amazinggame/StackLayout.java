package de.spitak.amazinggame;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by rschlett on 10/25/16.
 */

public class StackLayout extends ViewGroup {
    private int deviceWidth;

    public StackLayout(Context context) {
        super(context);
        init(context);
    }

    public StackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public StackLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        deviceWidth = deviceDisplay.x;
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final DisplayMetrics displayMetrics = Utils.getDisplayMetrics(getContext());
        final int Z_DIFF = 50;
        final int Y_DIFF = Utils.dpToPx(displayMetrics, 10);
        final int CHILD_COUNT = getChildCount();

        for (int i = 0, j = CHILD_COUNT; i < CHILD_COUNT; i++, j--) {
            View child = getChildAt(i);

            final int MARGIN = (int) child.getElevation();


            child.layout(MARGIN, MARGIN + Y_DIFF * i, r - l - MARGIN, b - t - MARGIN - Y_DIFF * j);
            child.setElevation(child.getElevation() * j);
            //child.setZ(Z_DIFF * j);
        }

        /*final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop, maxHeight;

        //get the available size of child view
        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;

        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == GONE)
                return;

            //Get the maximum size of the child
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
            curWidth = child.getMeasuredWidth();
            curHeight = child.getMeasuredHeight();
            //wrap is reach to the end
            if (curLeft + curWidth >= childRight) {
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = 0;
            }
            //do the layout
            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
            //store the max height
            if (maxHeight < curHeight)
                maxHeight = curHeight;
            curLeft += curWidth;
        }*/
    }

    // only a number which sorts / positions them
    public static class LayoutParams extends ViewGroup.LayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }


}
