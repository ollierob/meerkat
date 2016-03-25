package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.UnsupportedSecurityException;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author Ollie
 */
public interface PriceCalculator {

    @Nonnull
    Money price(@Nonnull SecurityDefinition security)
            throws UnsupportedSecurityException;

}
