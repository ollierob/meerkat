package net.meerkat.instrument;

import java.time.LocalDate;

import net.meerkat.security.Instrument;

/**
 *
 * @author ollie
 */
public interface OtcInstrument extends Instrument {
    
    LocalDate tradeDate();

}
