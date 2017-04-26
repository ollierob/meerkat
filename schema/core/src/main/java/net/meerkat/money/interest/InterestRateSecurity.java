package net.meerkat.money.interest;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;

/**
 *
 * @author Ollie
 */
public interface InterestRateSecurity extends Instrument {

    @Nonnull
    InterestRate interestRate();

}
