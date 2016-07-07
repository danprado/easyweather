package com.daniloprado.weather.util;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ViewFlipper;

public final class ViewFlipperUtil {

    private ViewFlipperUtil() {
    }

    public static boolean isValidChildIndex(@NonNull ViewFlipper flipper, int index) {
        return index < flipper.getChildCount()
                && index >= 0;
    }

    public static boolean isValidChild(@NonNull ViewFlipper flipper, View child) {
        int index = flipper.indexOfChild(child);
        return index < flipper.getChildCount()
                && index >= 0;
    }

    public static void setDisplayedChild(ViewFlipper flipper, View child) {
        if (flipper != null && child != null) {
            int index = flipper.indexOfChild(child);
            if (isValidChildIndex(flipper, index)) {
                flipper.setDisplayedChild(index);
            }
        }
    }

}
