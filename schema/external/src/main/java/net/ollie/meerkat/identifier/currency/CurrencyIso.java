package net.ollie.meerkat.identifier.currency;

import java.io.Serializable;

import net.ollie.goat.money.currency.Currency;
import net.ollie.meerkat.identifier.Iso;
import net.ollie.meerkat.identifier.country.CountryIso;
import net.ollie.meerkat.identifier.country.HasCountryId;
import net.ollie.meerkat.identifier.security.SecurityId;

/**
 * ISO 4217 currency code.
 *
 * @author Ollie
 */
public interface CurrencyIso
        extends Iso, Currency, HasCountryId, SecurityId, Serializable {

    default boolean isReserved() {
        return this.first() == 'X';
    }

    @Override
    default String uniqueSymbol() {
        return this.value() + this.symbol();
    }

    @Override
    @Deprecated
    default CurrencyIso currency() {
        return this;
    }

    @Override
    default CountryIso countryId() {
        return CountryIso.valueOf(this.value().substring(0, 2));
    }

    @Override
    default String standard() {
        return "4217";
    }

    AUD AUD = new AUD();
    CAD CAD = new CAD();
    CHF CHF = new CHF();
    CNY CNY = new CNY();
    CNH CNH = new CNH();
    EUR EUR = new EUR();
    GBP GBP = new GBP();
    INR INR = new INR();
    JPY JPY = new JPY();
    NOK NOK = new NOK();
    NZD NZD = new NZD();
    THB THB = new THB();
    TWD TWD = new TWD();
    USD USD = new USD();
    VND VND = new VND();
    ZAR ZAR = new ZAR();

    GBX GBX = new GBX();

    XAG XAG = new XAG();
    XAU XAU = new XAU();

    XTS XTS = new XTS();

}
