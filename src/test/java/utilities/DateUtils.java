package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static String[] returnNextMonth(){
        // Create Date object
        Date dNow = new Date();
        // Crate calendar object for Gregorian Calendar
        Calendar calendar = new GregorianCalendar();
        //Set calendar object to current date
        calendar.setTime(dNow);
        //Create object for SimpleDateFormat
        //Defining Date format to - example:(aug-2022)
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
        //Increment the current month
        calendar.add(calendar.MONTH,1);
        //generate the date base on the specified object
        String date = sdf.format(calendar.getTime());
        //Returning the value of month and year in an Array
        return date.split("-");
    }
}
