package net.meerkat.time.calendar.business;

import java.time.LocalDate;

import net.coljate.cache.Cache;
import net.meerkat.utils.Constructor;

/**
 *
 * @author ollie
 */
public interface BusinessDayCache extends Cache<LocalDate, BusinessDay> {

    Constructor<LocalDate, BusinessDay> CONSTRUCTOR = Constructor.of(BusinessDay::new);

}
