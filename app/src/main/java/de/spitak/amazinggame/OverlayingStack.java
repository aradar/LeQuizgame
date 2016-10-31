package de.spitak.amazinggame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by dephiloper on 27.10.16.
 */

public class OverlayingStack extends RelativeLayout {

    public OverlayingStack(Context context) {
        super(context);
    }

    public OverlayingStack(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverlayingStack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OverlayingStack(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        super.onLayout(changed, l, t, r, b);
    }
}
