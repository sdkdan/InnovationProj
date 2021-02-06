package ru.innovat.service.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormatConfig {
    public static SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
    }
}
