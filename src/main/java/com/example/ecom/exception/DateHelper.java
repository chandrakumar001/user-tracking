package com.example.ecom.exception;

import com.example.ecom.exception.common.FieldValidationException;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private DateHelper() {
        throw new UnsupportedOperationException("DateHelper class");
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(DATE_FORMAT);
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(YYYY_MM_DD);


    public static String getDatetimeStamp(final Instant instant) {

        final LocalDateTime today = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return formatter1.format(today);
    }


    public static String getDatetimeStamp() {

        final LocalDateTime today = LocalDateTime.now();
        return formatter1.format(today);
    }

    public static void validateDateFormat(final String value, final String code) {

        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(code)) {
            throw new FieldValidationException(code);
        }

        try {
            LocalDate.parse(value);
        } catch (Exception e) {
            //Invalid date 'AvailableFrom': {0}, should be in 'yyyy-MM-dd' format
            //Property 'AvailableFrom' invalid value, must be in 'yyyy-MM-dd' format!
            throw new FieldValidationException(code);
        }
    }

    public static LocalDate toLocalDate(final String dateStr) {
        return LocalDate.parse(dateStr, formatter2);
    }

}
