package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import net.coljate.cache.Cache;

/**
 *
 * @author ollie
 */
public interface SettlementDateCache extends Cache<LocalDate, SettlementDate> {

    class Factory {

        protected SettlementDate create(final LocalDate date) {
            return new SettlementDate(date);
        }

    }

}
