package net.meerkat.instrument;

import java.time.LocalDate;

import javax.annotation.Nonnull;


/**
 *
 * @author ollie
 */
public interface OtcInstrument extends Instrument {

    @Nonnull
    LocalDate tradeDate();

}
