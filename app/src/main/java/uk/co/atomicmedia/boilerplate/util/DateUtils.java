package uk.co.atomicmedia.boilerplate.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String FORMAT_AUCTION_LIST_DATE = "EEE d'suffix' MMM";
    public static final String FORMAT_SAVED_SEARCH_CREATED = "d MMM yyyy";
    public static final String FORMAT_AUCTION_LIST_TIME = "HH:mm";
    public static final String FORMAT_API_DATETIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    // parse a string from ISO into a SDF format
    // additional feature: use 'suffix' as a placeholder to add a suffix eg "EEE d'suffix' MMM" will print "Tue 16th Oct"
    static public String parseAndFormatDate(String dateISO, String format){
        try {
            Date date = parseStringToLocalDate(dateISO);
            format = format.replace("suffix", getOrdinalSuffix(date));

            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    private static String getOrdinalSuffix(Date localTimeZoneDate) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(localTimeZoneDate);
        int day=cal.get(Calendar.DATE);

        if (day >= 11 && day <= 13) {
            return "th";
        }

        switch(day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }

    private static Date parseStringToLocalDate(String dateISO) throws ParseException{
        SimpleDateFormat parseFormat = new SimpleDateFormat(FORMAT_API_DATETIME);
        parseFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date localDateTime = parseFormat.parse(dateISO);
        return localDateTime;
    }

    public static String getTodayInApiFormat(){
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_API_DATETIME);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String todayUtcFormat = dateFormat.format(new Date());
        return todayUtcFormat;
    }

    public static boolean isSameDay(String utcDateString1, String utcDateString2){

        try {
            // Parse times from UTC format into Calendar objects
            Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal1.setTime(parseStringToLocalDate(utcDateString1));

            Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal2.setTime(parseStringToLocalDate(utcDateString2));

            // Change timezone to local before comparison
            cal1.setTimeZone(TimeZone.getDefault());
            cal2.setTimeZone(TimeZone.getDefault());

            return org.apache.commons.lang3.time.DateUtils.isSameDay(cal1, cal2);
        }
        catch (ParseException e) {
            return false;
        }
    }

    public static String formatTime(String timeString) {

        String hourPart = timeString.substring(0,2);
        String minutePart = timeString.substring(2);

        return FORMAT_AUCTION_LIST_TIME.replace("HH", hourPart).replace("mm", minutePart);
    }
}
