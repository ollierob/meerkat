package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import net.coljate.cache.Cache;

/**
 *
 * @author ollie
 */
public interface HolidayCache extends Cache<LocalDate, Holiday> {

    class Factory {

        protected Holiday create(final LocalDate date) {
            return new Holiday(date);
        }

    }
    
}
