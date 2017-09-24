package net.meerkat.identifier.currency;

import java.io.Serializable;

import net.meerkat.identifier.Iso;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.money.MoneyFormat;

/**
 * ISO 4217 currency code.
 *
 * @author Ollie
 */
public interface CurrencyIso
        extends Iso, CurrencyId, InstrumentId, Serializable {

    default boolean isReserved() {
        return this.first() == 'X';
    }

    @Override
    default String uniqueSymbol() {
        return this.value();
    }

    @Override
    @Deprecated
    default CurrencyIso currencyId() {
        return this;
    }

    @Override
    default String standard() {
        return "4217";
    }

    @Override
    default MoneyFormat format() {
        return MoneyFormat.UNIQUE_SYMBOL_AMOUNT;
    }

    AUD AUD = new AUD();
    CAD CAD = new CAD();
    CHF CHF = new CHF();
    CNH CNH = new CNH();
    CNY CNY = new CNY();
    CZK CZK = new CZK();
    DKK DKK = new DKK();
    EUR EUR = new EUR();
    GBP GBP = new GBP();
    HKD HKD = new HKD();
    INR INR = new INR();
    JPY JPY = new JPY();
    MXN MXN = new MXN();
    NOK NOK = new NOK();
    NZD NZD = new NZD();
    PLN PLN = new PLN();
    QAR QAR = new QAR();
    RUB RUB = new RUB();
    SEK SEK = new SEK();
    SGD SGD = new SGD();
    THB THB = new THB();
    TRY TRY = new TRY();
    TWD TWD = new TWD();
    USD USD = new USD();
    VND VND = new VND();
    ZAR ZAR = new ZAR();

    GBX GBX = new GBX();

    XXX XXX = new XXX();

}
