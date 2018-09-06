package com.clearlane.offersengine_job.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Vivek Nambiar
 *
 */
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
    public static final String YEAR_FORMAT = "yyyy";

    public static String getDateAsString(Date date) {
        return getDateAsString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * format the date in mm/dd/yyyy
     * @return
     */
    public static String getDateAsString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null)
            return sdf.format(date);
        else
            return null;
    }

    public static Date getStringAsDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null)
            try {
                return sdf.parse(date);
            }
            catch (Exception e) {
                // TODO: handle exception
                return null;
            }
        else
            return null;
    }

    public static Date dateEnd(Date d) {
        if (d == null)
            d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));

        return cal.getTime();
    }

    /**
     * Returns months between the given dates. Pass the roundUpMonthCount if it has to 
     * round up the month count i.e count partial month in the end as a complete month 
     */
    public static Integer monthsBetweenDates(Date fromDate, Date toDate,
            Boolean roundUpMonthCount) {
        GregorianCalendar frmCal = new GregorianCalendar();
        frmCal.setTime(dateStart(fromDate));
        GregorianCalendar toCal = new GregorianCalendar();
        toCal.setTime(dateStart(toDate));

        int months = 0;
        frmCal.add(GregorianCalendar.MONTH, 1); // add month
        while (frmCal.before(toCal) || frmCal.equals(toCal)) {
            months++;
            frmCal.add(GregorianCalendar.MONTH, 1);
        }

        if (roundUpMonthCount) {
            frmCal.add(GregorianCalendar.MONTH, -1); // reduce month
            if (frmCal.before(toCal))
                months++;
        }

        return months;
    }

    /**
     * Returns the first millisecond of a given Date
     * @param d
     * @return
     */
    public static Date dateStart(Date d) {
        if (d == null)
            d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));

        return cal.getTime();
    }

    /**
     * This will return the current year. Like 2017, 2018..
     * 
     * @return
     */
    public static int getCurrentYear() {
        Calendar now = Calendar.getInstance(); // Gets the current date and time
        return now.get(Calendar.YEAR); // The current year
    }

    /**
     * If given dates are valid this method will return years and months difference in yy-mm format else null
     * 
     * @param startDate in yyyy-MM format
     * @param endDate in yyyy-MM format
     * @return Year and month difference in yy-MM format like 2-10
     */
    public static String getYearMonthDifference(YearMonth startDate, YearMonth endDate) {
        String yearMonthDiff = null;

        if (startDate != null && endDate != null) {
            long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate) + 1;
            long yearsBetween = monthsBetween / 12;
            long monthsRemaining = monthsBetween % 12;

            yearMonthDiff = yearsBetween + "-" + monthsRemaining;
        }

        return yearMonthDiff;
    }

    /**
     * Gets No of days between startDate and endDate.
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getNoOfDays(LocalDate startDate, LocalDate endDate) {

        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Compares  passed 'date'  with current date and returns true if 'date' is before 'expiresInDays' number of days to the current date
     * @param date
     * @param expiresInDays
     * @return true if passed date is before the expiresInDays wrt to current date
     */
    public static boolean isDateExpired(Date date, int expiresInDays) {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastUpdatedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return DateUtils.getNoOfDays(lastUpdatedDate, currentDate) > expiresInDays;

    }
}
