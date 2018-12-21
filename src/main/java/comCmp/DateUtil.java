package comCmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String SUFFIX_START_TIME = " 00:00:00";
    public static String SUFFIX_END_TIME = " 23:59:59";



    /**
     * {date}日期的{days}天前
     *
     * @param date
     * @param days
     * @return
     */
    public static Date lastNDays(Date date, int days) {
        return afterNDays(date, -days);
    }

    /**
     * {date}日期的{days}天后
     *
     * @param date
     * @param days
     * @return
     */
    public static Date afterNDays(Date date, int days) {

        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        Date resultDate = c.getTime();
        return resultDate;
    }

}
