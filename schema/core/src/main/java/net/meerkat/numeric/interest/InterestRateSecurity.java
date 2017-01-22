package net.meerkat.numeric.interest;

import javax.annotation.Nonnull;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public interface InterestRateSecurity extends Security {

    @Nonnull
    InterestRate interestRate();

}
