package net.ollie.meerkat.identifier.currency;

import java.io.Serializable;

import net.ollie.meerkat.identifier.Iso;
import net.ollie.meerkat.identifier.country.CountryIso;
import net.ollie.meerkat.identifier.country.HasCountryId;
import net.ollie.meerkat.identifier.security.SecurityId;

/**
 * ISO 4217 currency code.
 *
 * @author Ollie
 */
public interface CurrencyIso extends Iso, CurrencyId, HasCountryId, SecurityId, Serializable {

    GBP GBP = new GBP();
    USD USD = new USD();
    EUR EUR = new EUR();
    JPY JPY = new JPY();

    String symbol();

    default String uniqueSymbol() {
        return this.countryId() + this.symbol();
    }

    @Override
    default CurrencyIso currency() {
        return this;
    }

    default boolean isReserved() {
        return this.first() == 'X';
    }

    @Override
    default CountryIso countryId() {
        return CountryIso.valueOf(this.value().substring(0, 2));
    }

    @Override
    default String standard() {
        return "4217";
    }

}
