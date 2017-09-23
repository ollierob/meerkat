package net.meerkat.time.calendar.business;

import java.time.LocalDate;

import net.coljate.cache.Cache;

/**
 *
 * @author ollie
 */
public interface BusinessDayCache extends Cache<LocalDate, BusinessDay> {

}
