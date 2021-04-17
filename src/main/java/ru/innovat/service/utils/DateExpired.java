package ru.innovat.service.utils;

import java.util.Date;

public class DateExpired {

    public static boolean isExpired(Date date) {
        return date.after(new Date());
    }
}
