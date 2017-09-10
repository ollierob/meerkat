package net.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.function.Function;

import net.ollie.goat.temporal.date.HasDate;

/**
 *
 * @author Ollie
 */
public class NotWeekendCalendar<D extends HasDate> implements Calendar<D> {

    private final boolean weekendsForward;
    private final Function<LocalDate, D> toDate;

    protected NotWeekendCalendar(final boolean weekendsForward, final Function<LocalDate, D> toDate) {
        this.weekendsForward = weekendsForward;
        this.toDate = toDate;
    }

    @Override
    public boolean isInRange(LocalDate date) {
        return true;
    }

    protected D forward(final LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SATURDAY:
                return toDate.apply(date.plusDays(2));
            case SUNDAY:
                return toDate.apply(date.plusDays(1));
            default:
                return toDate.apply(date);
        }
    }

    protected D back(final LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SATURDAY:
                return toDate.apply(date.minusDays(1));
            case SUNDAY:
                return toDate.apply(date.minusDays(2));
            default:
                return toDate.apply(date);
        }
    }

    @Override
    public D next(final LocalDate date) {
        return weekendsForward ? this.forward(date) : this.back(date);
    }

}
