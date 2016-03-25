package net.ollie.meerkat.identifier.security;

import java.time.Month;
import java.time.YearMonth;
import java.util.function.Function;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FutureTicker implements SecurityId {

    @XmlAttribute(name = "underlying", required = true)
    private String underlying;

    @XmlAttribute(name = "delivery", required = true)
    private YearMonth delivery;

    @XmlAttribute(name = "suffix", required = false)
    private String suffix;

    @Deprecated
    FutureTicker() {
    }

    public FutureTicker(final String underlying, final YearMonth delivery, final String suffix) {
        this.underlying = underlying;
        this.delivery = delivery;
        this.suffix = suffix;
    }

    public String underlying() {
        return underlying;
    }

    public YearMonth yearMonth() {
        return delivery;
    }

    public String suffix() {
        return suffix;
    }

    public String toString(final Function<YearMonth, String> dateToString) {
        return underlying
                + dateToString.apply(delivery)
                + (suffix == null ? "" : ' ' + suffix);
    }

    @Override
    public String toString() {
        return this.toString(DeliveryMonth::toString);
    }

    public enum DeliveryMonth {

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

        private DeliveryMonth(final char m) {
            this.c = m;
        }

        public char character() {
            return c;
        }

        public Month month() {
            return Month.of(this.ordinal() + 1);
        }

        private static final DeliveryMonth[] VALUES;
        private static final int OFFSET = 65;

        static {
            VALUES = new DeliveryMonth[26];
            for (final DeliveryMonth month : values()) {
                VALUES[month.c - OFFSET] = month;
            }
        }

        public static DeliveryMonth valueOf(final char c) {
            final int ordinal = (int) Character.toUpperCase(c) - OFFSET;
            return VALUES[ordinal];
        }

        public static DeliveryMonth valueOf(final Month month) {
            return DeliveryMonth.values()[month.getValue() - 1];
        }

        public static String toString(final YearMonth yearMonth) {
            return ""
                    + valueOf(yearMonth.getMonth()).character()
                    + Character.digit(yearMonth.getYear() % 10, 10);
        }

    }

}
