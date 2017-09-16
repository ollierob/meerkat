package net.meerkat.time.calendar.business;

import java.time.LocalDate;

/**
 *
 * @author Ollie
 */
public abstract class NotWeekendBusinessDays implements BusinessDayCalendar {

    public static final NotWeekendBusinessDays WEEKEND_TO_MONDAY = new NotWeekendBusinessDays() {

        @Override
        public BusinessDay next(final LocalDate date) {
            switch (date.getDayOfWeek()) {
                case SATURDAY:
                    return new BusinessDay(date.plusDays(2));
                case SUNDAY:
                    return new BusinessDay(date.plusDays(1));
                default:
                    return new BusinessDay(date);
            }
        }

        @Override
        public BusinessDay previous(final LocalDate date) {
            switch (date.getDayOfWeek()) {
                case SATURDAY:
                    return new BusinessDay(date.minusDays(1));
                case SUNDAY:
                    return new BusinessDay(date.minusDays(2));
                default:
                    return new BusinessDay(date);
            }
        }

    };

    private NotWeekendBusinessDays() {
    }

    @Override
    public boolean contains(final LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SATURDAY:
            case SUNDAY:
                return false;
            default:
                return true;
        }
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return true;
    }

}
