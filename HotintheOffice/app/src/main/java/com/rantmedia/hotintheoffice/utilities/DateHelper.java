package com.rantmedia.hotintheoffice.utilities;

import android.util.Log;

import org.joda.time.DateTime;

/**
 * Created by ginobasiletti on 28/07/2017.
 */

public class DateHelper {


    public static String utcToPrettyDate(long utc) {
        DateTime dateTime = new DateTime(utc);

        String hourofday = String.valueOf(dateTime.getHourOfDay());
        String minuteOfHour = String.valueOf(dateTime.getMinuteOfHour());

        if (dateTime.getHourOfDay() < 10) {
            hourofday = "0"+ dateTime.getHourOfDay() ;
        }

        if (dateTime.getMinuteOfHour() < 10) {
            minuteOfHour = "0" + dateTime.getMinuteOfHour();
        }


        return hourofday + "." + minuteOfHour;

    }

}
