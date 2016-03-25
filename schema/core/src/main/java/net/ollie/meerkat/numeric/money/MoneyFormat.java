package net.ollie.meerkat.numeric.money;

import net.ollie.meerkat.identifier.currency.Currency;
import net.ollie.meerkat.identifier.currency.CurrencyId;

/**
 *
 */
public enum MoneyFormat {

    SYMBOL_AMOUNT {

        @Override
        public String toString(final Money<?> money) {
            return this.symbol(money.currencyId()) + money.amount();
        }

        private String symbol(final CurrencyId currency) {
            return currency instanceof Currency
                    ? ((Currency) currency).uniqueSymbol()
                    : currency.currencyId().toString();
        }

    },
    CURRENCY_AMOUNT {

        @Override
        public String toString(final Money<?> money) {
            return money.currencyId() + " " + money.amount();
        }

    },
    AMOUNT_CURRENCY {

        @Override
        public String toString(final Money<?> money) {
            return money.amount() + " " + money.currencyId();
        }

    };

    public abstract String toString(Money<?> money);

}
