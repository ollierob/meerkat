package net.meerkat.numeric.interest;

import javax.annotation.Nonnull;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.security.Instrument;

/**
 *
 * @author Ollie
 */
public interface InterestRateSecurity extends Instrument {

    @Nonnull
    InterestRate interestRate();

}
