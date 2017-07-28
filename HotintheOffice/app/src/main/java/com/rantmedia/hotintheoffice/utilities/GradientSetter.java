package com.rantmedia.hotintheoffice.utilities;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.rantmedia.hotintheoffice.R;


/**
 * Created by ginobasiletti on 30/11/2016.
 */

public class GradientSetter {

    public static void setGradientTwoColours(View view, Context context, int topColour, int bottomColour) {
        GradientDrawable gd = new GradientDrawable
                (GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
                        ContextCompat.getColor(context, topColour),
//                        ContextCompat.getColor(context, topColour),
                        ContextCompat.getColor(context, bottomColour)
                });
        view.setBackground(gd);
    }

    public static void setGradientThreeColours(View view, Context context, int topColour, int bottomColour) {
        GradientDrawable gd = new GradientDrawable
                (GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
                        ContextCompat.getColor(context, topColour),
                        ContextCompat.getColor(context, topColour),
                        ContextCompat.getColor(context, bottomColour)
                });
        view.setBackground(gd);
    }


        /*public enum Orientation {

        *//** draw the gradient from the top to the bottom *//*
        TOP_BOTTOM,

        *//** draw the gradient from the top-right to the bottom-left *//*
        TR_BL,

        *//** draw the gradient from the right to the left *//*
        RIGHT_LEFT,

        *//** draw the gradient from the bottom-right to the top-left *//*
        BR_TL,

        *//** draw the gradient from the bottom to the top *//*
        BOTTOM_TOP,

        *//** draw the gradient from the bottom-left to the top-right *//*
        BL_TR,

        *//** draw the gradient from the left to the right *//*
        LEFT_RIGHT,

        *//** draw the gradient from the top-left to the bottom-right *//*
        TL_BR,
    }*/

}
