package net.meerkat.time.calendar.business;

import java.time.LocalDate;

import net.coljate.cache.Cache;
import net.meerkat.utils.Protected;

/**
 *
 * @author ollie
 */
public interface BusinessDayCache extends Cache<LocalDate, BusinessDay> {

    Protected<LocalDate, BusinessDay> CONSTRUCTOR = Protected.of(BusinessDay::new);

}
