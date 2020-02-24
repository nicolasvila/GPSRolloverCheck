package fr.nicolasvila.gpsrollovercheck;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class GpsRolloverCheckTest {

    /**
     * Date between 1st and 2nd rollover date
     */
    private static final Date FAULTY_DATE = new GregorianCalendar(2000, 7, 1).getTime();

    /**
     * Date after 2nd rollover date
     */
    private static final Date VALID_DATE = new GregorianCalendar(2020, 7, 1).getTime();

    @Test
    public void checkFaultyGpsClock() {
        boolean gpsClockFaulty = GpsRolloverCheck.isGpsClockFaulty(FAULTY_DATE.getTime());
        assert gpsClockFaulty;
    }

    @Test
    public void checkValidGpsClock() {
        boolean gpsClockFaulty = GpsRolloverCheck.isGpsClockFaulty(VALID_DATE.getTime());
        assert !gpsClockFaulty;
    }

    @Test
    public void checkAndFixFaultyGpsClock() {
        Date fixedDate = new Date(GpsRolloverCheck.checkAndFixGpsTime(FAULTY_DATE.getTime()));
        assert FAULTY_DATE.before(fixedDate);
    }

    @Test
    public void checkAndFixValidGpsClock() {
        Date fixedDate = new Date(GpsRolloverCheck.checkAndFixGpsTime(VALID_DATE.getTime()));
        assert VALID_DATE.equals(fixedDate);
    }
}