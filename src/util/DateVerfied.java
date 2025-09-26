package util;

import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class DateVerfied {

    public static LocalDate DateNowVirfied(LocalDate date) {
        LocalDate now = LocalDate.now();
        if (date == null || date.isBefore(now) ) {
            return now;
        } else {
            return date;

        }
    }

    public static LocalDate datefinSansEng(LocalDate date) {
        LocalDate newDate = date.plusMonths(1);
        return newDate;
    };

    public static LocalDate dateFinAvecEng( LocalDate dateFin, int dureeEngagement) {
        LocalDate newDate = dateFin.plusMonths(dureeEngagement);
        return newDate;
    };

    public static LocalDate dateNow( ) {
        LocalDate now = LocalDate.now();
        return now;
    }
}
