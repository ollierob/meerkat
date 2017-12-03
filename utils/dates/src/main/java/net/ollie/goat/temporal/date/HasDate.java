package net.ollie.goat.temporal.date;

import javax.annotation.Nonnull;
import java.time.*;

/**
 * @author Ollie
 */
public interface HasDate {

    @Nonnull
    LocalDate date();

    @Nonnull
    default Year year() {
        return Year.of(this.date().getYear());
    }

    @Nonnull
    default Month month() {
        return this.date().getMonth();
    }

    @Nonnull
    default YearMonth yearMonth() {
        final LocalDate date = this.date();
        return YearMonth.of(date.getYear(), date.getMonth());
    }

    @Nonnull
    default MonthDay monthDay() {
        final LocalDate date = this.date();
        return MonthDay.of(date.getMonth(), date.getDayOfMonth());
    }

    default boolean isOnWeekday() {
        return !this.isOnWeekend();
    }

    default boolean isOnWeekend() {
        switch (this.date().getDayOfWeek()) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

    default boolean is(final LocalDate date) {
        return date != null && this.date().compareTo(date) == 0;
    }

    static int compareByDate(final HasDate left, final HasDate right) {
        return left.date().compareTo(right.date());
    }

}
