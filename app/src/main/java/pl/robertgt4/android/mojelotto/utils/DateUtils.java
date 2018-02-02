package pl.robertgt4.android.mojelotto.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by RRogowski on 2018-01-30.
 * użytki związane z manipulacjami na datach
 */

public class DateUtils {

    public static long dateToMillis(String dataString) {

        //2018-01-27 00:00:00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        long timeInMillis=0;
        if (! dataString.equals("")) {
            try {
                Date data = sdf.parse(dataString);
                timeInMillis=data.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return timeInMillis;

    }

    public static String millisToDate(long millis) {

        if (millis!=0) {
            SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyy-MMMM-dd"));
            //String retVal = DateFormat.getBestDateTimePattern( )
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(millis);
            return sdf.format(cal.getTime());
        } else {
            return "";
        }

    }

}
