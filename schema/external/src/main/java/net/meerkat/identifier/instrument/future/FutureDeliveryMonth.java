package net.meerkat.identifier.instrument.future;

import java.time.Month;
import java.time.YearMonth;

/**
 *
 * @author ollie
 */
public enum FutureDeliveryMonth {

    JANUARY('F'),
    FEBRUARY('G'),
    MARCH('H'),
    APRIL('J'),
    MAY('K'),
    JUNE('M'),
    JULY('N'),
    AUGUST('Q'),
    SEPTEMBER('U'),
    OCTOBER('V'),
    NOVEMBER('X'),
    DECEMBER('Z');

    private final char c;

    private FutureDeliveryMonth(final char m) {
        this.c = m;
    }

    public char character() {
        return c;
    }

    public Month month() {
        return Month.of(this.ordinal() + 1);
    }

    private static final FutureDeliveryMonth[] VALUES;
    private static final int OFFSET = 65;

    static {
        VALUES = new FutureDeliveryMonth[26];
        for (final FutureDeliveryMonth month : FutureDeliveryMonth.values()) {
            VALUES[month.c - OFFSET] = month;
        }
    }

    public static FutureDeliveryMonth valueOf(final char c) {
        final int ordinal = (int) Character.toUpperCase(c) - OFFSET;
        return VALUES[ordinal];
    }

    public static FutureDeliveryMonth valueOf(final Month month) {
        return FutureDeliveryMonth.values()[month.getValue() - 1];
    }

    public static String toString(final YearMonth yearMonth) {
        return "" + valueOf(yearMonth.getMonth()).character() + Character.digit(yearMonth.getYear() % 10, 10);
    }

}
