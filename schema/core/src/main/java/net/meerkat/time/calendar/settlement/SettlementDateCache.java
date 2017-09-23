package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import net.coljate.cache.Cache;
import net.meerkat.utils.Constructor;

/**
 *
 * @author ollie
 */
public interface SettlementDateCache extends Cache<LocalDate, SettlementDate> {
    
    Constructor<LocalDate, SettlementDate> CONSTRUCTOR = Constructor.of(SettlementDate::new);

}
