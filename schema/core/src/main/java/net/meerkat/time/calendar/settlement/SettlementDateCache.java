package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import net.coljate.cache.Cache;
import net.meerkat.utils.Protected;

/**
 *
 * @author ollie
 */
public interface SettlementDateCache extends Cache<LocalDate, SettlementDate> {
    
    Protected<LocalDate, SettlementDate> CONSTRUCTOR = Protected.of(SettlementDate::new);

}
