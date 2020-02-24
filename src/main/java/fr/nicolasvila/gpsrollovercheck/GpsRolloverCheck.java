package fr.nicolasvila.gpsrollovercheck;

import java.util.Date;

public class GpsRolloverCheck {

    /**
     * Check if GPS timestamp is faulty
     *
     * @param gpsTime gps timestamp
     * @return true if faulty
     */
    public static boolean isGpsClockFaulty(final long gpsTime) {
        return isGpsClockFaulty(gpsTime, System.currentTimeMillis());
    }

    /**
     * Check if GPS Date is faulty
     *
     * @param gpsDate gps date
     * @return true if faulty
     */
    public static boolean isGpsClockFaulty(final Date gpsDate) {
        return isGpsClockFaulty(gpsDate.getTime(), System.currentTimeMillis());
    }

    /**
     * Check if timestamp is faulty
     *
     * @param gpsTime       gps timestamp
     * @param referenceTime reference system time
     * @return true if faulty
     */
    public static boolean isGpsClockFaulty(final long gpsTime, final long referenceTime) {
        //TODO: only valid until 2038 November 20th ;-)
        return ((gpsTime > GpsRolloverConstants.getFirstRolloverDate().getTime()) &&
                (gpsTime < GpsRolloverConstants.getSecondRolloverDate().getTime()));
    }

    /**
     * Check and fix GPS timestamp if faulty
     *
     * @param gpsTime gps timestamp
     * @return fixed timestamp
     */
    public static long checkAndFixGpsTime(final long gpsTime) {
        return checkAndFixGpsTime(gpsTime, System.currentTimeMillis());
    }

    /**
     * Check and fix GPS date if faulty
     *
     * @param gpsDate gps date
     * @return fixed date
     */
    public static Date checkAndFixGpsTime(final Date gpsDate) {
        long fixedTime = checkAndFixGpsTime(gpsDate.getTime(), System.currentTimeMillis());
        return new Date(fixedTime);
    }

    /**
     * Check and fix GPS timestamp if faulty
     *
     * @param gpsTime       gps timestamp
     * @param referenceTime reference system time
     * @return fixed timestamp
     */
    public static long checkAndFixGpsTime(final long gpsTime, final long referenceTime) {
        if (isGpsClockFaulty(gpsTime)) {
            // We keep GPS timestamp precision, we only shift by 1024 weeks
            return gpsTime + GpsRolloverConstants.ROLLOVER_CYCLE_IN_MILLIS;
        }
        return gpsTime;
    }
}
