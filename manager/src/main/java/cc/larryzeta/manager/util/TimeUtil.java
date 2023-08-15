package cc.larryzeta.manager.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static Date getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }


    public static Date getTimeAfter(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date getTimeAfter(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
