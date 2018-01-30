package pl.robertgt4.android.mojelotto.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RRogowski on 2018-01-30.
 * użytki związane z manipulacjami na datach
 */

public class DateUtils {

    public static long dateToMillis(String dataString) {

        //2018-01-27 00:00:00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long timeInMillis=0;
        try {
            Date data = sdf.parse(dataString);
            timeInMillis=data.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeInMillis;

    }

}
