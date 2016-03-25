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

    CAD CAD = new CAD();
    CNY CNY = new CNY();
    EUR EUR = new EUR();
    GBP GBP = new GBP();
    JPY JPY = new JPY();
    USD USD = new USD();

}
