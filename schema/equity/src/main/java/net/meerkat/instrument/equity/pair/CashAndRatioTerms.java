package net.meerkat.instrument.equity.pair;

import net.meerkat.money.Money;

import static net.meerkat.identifier.currency.CurrencyIso.USD;

public record CashAndRatioTerms(double ratio, Money<?> cash) {

    static final CashAndRatioTerms NONE = new CashAndRatioTerms(Double.NaN, Money.of(USD, 0));

}
