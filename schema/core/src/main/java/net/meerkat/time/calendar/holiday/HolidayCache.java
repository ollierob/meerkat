package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import net.coljate.cache.Cache;
import net.meerkat.utils.Constructor;

/**
 *
 * @author ollie
 */
public interface HolidayCache extends Cache<LocalDate, Holiday> {

    Constructor<LocalDate, Holiday> CONSTRUCTOR = Constructor.of(Holiday::new);
    
}
