package net.ollie.meerkat.money;

import javax.annotation.Nonnull;

/**
 *
 */
public interface MoneyFormat {

    @Nonnull
    String toString(@Nonnull Money<?> money);

    MoneyFormat SYMBOL_AMOUNT = money -> money.currency().uniqueSymbol() + " " + money.amount();
    MoneyFormat AMOUNT_SYMBOL = money -> money.amount() + " " + money.currency().uniqueSymbol();
    MoneyFormat CURRENCY_AMOUNT = money -> money.currency() + " " + money.amount();
    MoneyFormat AMOUNT_CURRENCY = money -> money.amount() + " " + money.currency();

}
