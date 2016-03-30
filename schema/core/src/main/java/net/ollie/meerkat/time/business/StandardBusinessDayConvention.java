package net.ollie.meerkat.time.business;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public enum StandardBusinessDayConvention implements BusinessDayConvention {

    NONE {

        @Override
        public LocalDate adjust(LocalDate date) {
            return date;
        }

    },
    WEEKEND_TO_MONDAY {

        @Override
        public LocalDate adjust(LocalDate date) {
            switch (date.getDayOfWeek()) {
                case SUNDAY:
                    return date.plusDays(1);
                case SATURDAY:
                    return date.plusDays(2);
                default:
                    return date;
            }
        }

    },
    WEEKEND_TO_FRIDAY {

        @Override
        public LocalDate adjust(LocalDate date) {
            switch (date.getDayOfWeek()) {
                case SUNDAY:
                    return date.minusDays(2);
                case SATURDAY:
                    return date.minusDays(1);
                default:
                    return date;
            }
        }

    };

}
