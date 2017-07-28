package com.rantmedia.hotintheoffice.utilities;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Window;

/**
 * Created by ginobasiletti on 28/07/2017.
 */

public class StatusBarHelper {

    public static void setStatusBarColour(Window theWindow, Context mContext, int colour) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (theWindow != null) {
                theWindow.setStatusBarColor(ContextCompat.getColor(mContext, colour)); //Sets the colour for the bar at the very top.
            }
        }
    }

}
