package java.easter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EasterChallenge2022Java {
    public static Calendar calculateEasterSunday(int year) {

        int a = year % 19,
            b = year / 100,
            c = year % 100,
            d = b / 4,
            e = b % 4,
            g = (8 * b + 13) / 25,
            h = (19 * a + b - d - g + 15) % 30,
            j = c / 4,
            k = c % 4,
            m = (a + 11 * h) / 319,
            r = (2 * e + 2 * j - k - h + m + 32) % 7,
            n = (h - m + r + 90) / 25,
            p = (h - m + r + n + 19) % 32;

        Calendar cal = Calendar.getInstance();
        cal.set(year, n-1, p);

        return cal;
    }

    public static List<Calendar> calculateEasterDays(int year) {

        Calendar sunday = calculateEasterSunday(year);

        Calendar friday = (Calendar) sunday.clone();
        friday.add(Calendar.DAY_OF_MONTH, -2);

        Calendar monday = (Calendar) sunday.clone();
        monday.add(Calendar.DAY_OF_MONTH, 1);

        List<Calendar> list = new ArrayList<>();
        list.add(friday);
        list.add(sunday);
        list.add(monday);

        return list;
    }
}