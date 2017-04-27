package net.meerkat.money.interest;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;

/**
 *
 * @author Ollie
 */
public interface InterestRateInstrument extends Instrument {

    @Nonnull
    InterestRate interestRate();

}
