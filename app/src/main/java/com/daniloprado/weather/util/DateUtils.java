package com.daniloprado.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtils {

    public static String getDayOfTheWeekFromUnixTime(int unixTime) {
        Date date = new Date((long) unixTime*1000);
        return new SimpleDateFormat("EE", Locale.getDefault()).format(date);
    }

}
