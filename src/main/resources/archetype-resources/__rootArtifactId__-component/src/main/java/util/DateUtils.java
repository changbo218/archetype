#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 日期工具类
 */
@Component
public class DateUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date to Localdate/LocalDateTime
     */
    public static LocalDate getLocalDate(Date date) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * String to Localdate/LocalDateTime
     */
    public static LocalDate getLocalDate(String s) {
        if (ObjectUtils.isEmpty(s)) {
            return null;
        }
        return LocalDate.parse(s, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static LocalDateTime getLocalDateTime(String s) {
        if (ObjectUtils.isEmpty(s)) {
            return null;
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }

    /**
     * Localdate/LocalDateTime to String
     */
    public static String getDatePattern(LocalDate localDate) {
        if (ObjectUtils.isEmpty(localDate)) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static String getDateTimePattern(LocalDateTime localDateTime) {
        if (ObjectUtils.isEmpty(localDateTime)) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }

    /**
     * Date to String
     */
    public static String getDatePattern(Date date) {
        return getDatePattern(getLocalDate(date));
    }

    public static String getDateTimePattern(Date date) {
        return getDateTimePattern(getLocalDateTime(date));
    }
}
