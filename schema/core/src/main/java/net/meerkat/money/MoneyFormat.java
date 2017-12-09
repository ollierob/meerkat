package net.meerkat.money;

import javax.annotation.Nonnull;

/**
 *
 */
public interface MoneyFormat {

    @Nonnull
    String toString(@Nonnull Money<?> money);

    MoneyFormat PER_CURRENCY = money -> money.currencyId().format().toString(money);
    MoneyFormat UNIQUE_SYMBOL_AMOUNT = money -> money.currencyId().uniqueSymbol() + ' ' + money.value();
    MoneyFormat AMOUNT_UNIQUE_SYMBOL = money -> money.value() + " " + money.currencyId().uniqueSymbol();
    MoneyFormat CURRENCY_AMOUNT = money -> money.currencyId() + " " + money.value();
    MoneyFormat AMOUNT_CURRENCY = money -> money.value() + " " + money.currencyId();

}
