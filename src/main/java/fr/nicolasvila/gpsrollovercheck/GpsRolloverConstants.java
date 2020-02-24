package fr.nicolasvila.gpsrollovercheck;

import java.util.Date;
import java.util.GregorianCalendar;

public interface GpsRolloverConstants {

    /**
     * GPS week cycle   Start of week 0      End of week 1023
     * 1                January 6, 1980.     August 21, 1999
     * (44244)              (51411)
     * 2                August 22, 1999      April 6, 2019
     * (51412)              (58579)
     * 3                April 7, 2019        November 20, 2038
     * (58580)              (65747)
     * <p>
     * Note: The numbers in parentheses are the
     * corresponding modified Julian dates, a running
     * count of elapsed days since midnight beginning
     * November 17, 1858 (Julian date 2400000.5).
     * https://www2.unb.ca/gge/Resources/gpsworld.november98.pdf
     */

    /**
     * Miliseconds in 1 day
     */
    public static final long ONE_DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    /**
     * Miliseconds in 1 GPS cycle
     */
    public static final long ROLLOVER_CYCLE_IN_MILLIS = 1024 * 7 * ONE_DAY_IN_MILLIS;

    /**
     * @return the 1st rollover date
     */
    public static Date getFirstRolloverDate() {
        return new GregorianCalendar(1999, 8, 21).getTime();
    }

    /**
     * @return the 2nd rollover date
     */
    public static Date getSecondRolloverDate() {
        return new GregorianCalendar(2019, 4, 6).getTime();
    }

    /**
     * @return the 3rd rollover date
     */
    public static Date getThirdRolloverDate() {
        return new GregorianCalendar(2038, 11, 20).getTime();
    }
}
