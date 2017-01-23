package net.meerkat.instrument;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.security.Instrument;

/**
 *
 * @author ollie
 */
public interface OtcInstrument extends Instrument {

    @Nonnull
    LocalDate tradeDate();

}
