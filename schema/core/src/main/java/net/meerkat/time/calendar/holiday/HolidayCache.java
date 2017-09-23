package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import net.coljate.cache.Cache;
import net.meerkat.utils.Protected;

/**
 *
 * @author ollie
 */
public interface HolidayCache extends Cache<LocalDate, Holiday> {

    Protected<LocalDate, Holiday> CONSTRUCTOR = Protected.of(Holiday::new);
    
}
