package ru.innovat.models.utils;


import java.util.Calendar;
import java.util.Date;

public class CalculateExpiryDate {

    private static final int EXPIRATION = 60 * 24;

    public static Date calculateDate() {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, EXPIRATION);
        return cal.getTime();
    }
}
