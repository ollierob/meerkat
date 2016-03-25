package net.ollie.meerkat.identifier.currency;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public interface Currency extends HasCurrencyId, Security {

    Percentage STANDARD_PIP = Percentage.basisPoints(1);

    @Override
    default Currency security() {
        return this;
    }

    @Nonnull
    default Percentage pip() {
        return STANDARD_PIP;
    }

}
