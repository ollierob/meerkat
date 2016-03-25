package net.ollie.meerkat.identifier.currency;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.country.HasCountryId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public interface Currency extends HasCurrencyId, HasCountryId, Security {

    Percentage STANDARD_PIP = Percentage.basisPoints(1);

    @Nonnull
    String symbol();

    @Nonnull
    default String uniqueSymbol() {
        return this.countryId() + this.symbol();
    }

    @Override
    default Currency security() {
        return this;
    }

    @Nonnull
    default Percentage pip() {
        return STANDARD_PIP;
    }

}
