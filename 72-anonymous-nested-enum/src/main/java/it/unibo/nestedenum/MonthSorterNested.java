package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        static Month fromString(final String month) {
            List<Month> matchedMonths = new LinkedList<>();

            for (Month m : values()) {
                if (m.toString().startsWith(month.toUpperCase())) {
                    matchedMonths.add(m);
                }
            }
            
            if (matchedMonths.size() != 1) throw new IllegalArgumentException();
                
            return matchedMonths.get(0);
        }
    }

    private class SortByDays implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            return Month.fromString(arg0).days - Month.fromString(arg1).days;
        }
    }

    private class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            return Month.fromString(arg0).compareTo(Month.fromString(arg1));
        }
        
    }
}

