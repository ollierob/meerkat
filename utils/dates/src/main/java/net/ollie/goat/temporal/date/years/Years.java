package net.ollie.goat.temporal.date.years;

import net.ollie.goat.numeric.Numeric;
import net.ollie.goat.temporal.TemporalToChrono;
import net.ollie.goat.temporal.date.Periods;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * @author ollie
 */
public interface Years extends TemporalToChrono, Comparable<Years>, Numeric.Summable<Years> {

    Years ZERO = IntegerYears.ZERO;
    Years ONE = IntegerYears.ONE;

    Period toPeriod(double daysPerYear);

    @Override
    default Period period() {
        return this.toPeriod(Periods.DAYS_PER_YEAR);
    }

    default int round(final RoundingMode rounding) {
        return this.decimalValue().setScale(0, rounding).intValue();
    }

    @Override
    default BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(Periods.approximateLength(this.period()));
    }

    @Nonnull
    default LocalDate addTo(final LocalDate date) {
        return date.plus(this.period());
    }

    @Override
    default Years plus(final Years that) {
        return new PeriodYears(this.period().plus(that.period()));
    }

    @Override
    default Years times(final Number that, final RoundingMode rounding) {
        return of(this.doubleValue() * that.doubleValue());
    }

    @Override
    default boolean isSupported(final ChronoUnit unit) {
        switch (unit) {
            case YEARS:
            case DECADES:
            case CENTURIES:
            case MILLENNIA:
            case ERAS:
                return true;
            default:
                return false;
        }
    }

    @Override
    default Years plus(final long amountToAdd, final ChronoUnit unit) {
        final long multiplier;
        switch (unit) {
            case YEARS:
                multiplier = 1;
                break;
            case DECADES:
                multiplier = 10;
                break;
            case CENTURIES:
                multiplier = 100;
                break;
            case MILLENNIA:
                multiplier = 1000;
                break;
            case ERAS:
                multiplier = 1_000_000_000;
                break;
            default:
                throw new UnsupportedTemporalTypeException(unit.name());
        }
        return this.plus(Years.of(Math.multiplyExact(amountToAdd, multiplier)));
    }

    @Override
    default boolean isSupported(final ChronoField field) {
        switch (field) {
            case YEAR:
                return true;
            default:
                return false;
        }
    }

    @Override
    default long getLong(final ChronoField field) {
        switch (field) {
            case YEAR:
                return this.decimalValue().longValue();
        }
        throw new UnsupportedTemporalTypeException(field.name());
    }

    static IntegerYears of(final int years) {
        return IntegerYears.of(years);
    }

    static Years of(final long years) {
        return of(Math.toIntExact(years));
    }

    static DoubleYears of(final double years) {
        return new DoubleYears(years);
    }

    static Years between(final LocalDate start, final LocalDate end) {
        return of(Period.between(start, end));
    }

    static Years of(Period period) {
        period = period.normalized();
        return period.getDays() == 0 && period.getMonths() == 0
                ? of(period.getYears())
                : new PeriodYears(period);
    }

}
