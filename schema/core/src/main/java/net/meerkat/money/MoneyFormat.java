package net.meerkat.money;

import javax.annotation.Nonnull;

/**
 *
 */
public interface MoneyFormat {

    @Nonnull
    String toString(@Nonnull Money<?> money);

    MoneyFormat SYMBOL_AMOUNT = money -> money.currencyId().uniqueSymbol() + " " + money.amount();
    MoneyFormat AMOUNT_SYMBOL = money -> money.amount() + " " + money.currencyId().uniqueSymbol();
    MoneyFormat CURRENCY_AMOUNT = money -> money.currencyId() + " " + money.amount();
    MoneyFormat AMOUNT_CURRENCY = money -> money.amount() + " " + money.currencyId();

}
