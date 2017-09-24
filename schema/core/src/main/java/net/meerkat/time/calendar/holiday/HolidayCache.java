package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.coljate.cache.Cache;
import net.coljate.cache.impl.EmptyCache;

/**
 *
 * @author ollie
 */
public interface HolidayCache extends Cache<LocalDate, Holiday> {

    @Override
    @Nonnull
    Holiday get(LocalDate date);

    @Nonnull
    default Holiday get(final LocalDate date, final String name) {
        return this.get(date).named(name);
    }

    class Factory extends EmptyCache<LocalDate, Holiday> implements HolidayCache {

        private static final long serialVersionUID = 1L;

        @Override
        public Holiday get(final LocalDate date) {
            return new Holiday(date);
        }

    }

}
