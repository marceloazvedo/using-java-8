package br.com.marceloazvedo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String NORTH_AMERICAN_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static String getDateFormated(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

}
