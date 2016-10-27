package de.spitak.amazinggame;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by rschlett on 10/24/16.
 */

public final class Utils {
    public static DisplayMetrics getDisplayMetrics(Context ctx) {
        return ctx.getResources().getDisplayMetrics();
    }

    public static int pxToDp(DisplayMetrics displayMetrics, int px) {
        return (int) (px / displayMetrics.density);
    }

    public static int dpToPx(DisplayMetrics displayMetrics, int dp) {
        return (int) (dp * displayMetrics.density);
    }
}
