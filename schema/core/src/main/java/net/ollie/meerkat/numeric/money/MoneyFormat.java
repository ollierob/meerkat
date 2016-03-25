package net.ollie.meerkat.numeric.money;

/**
 *
 */
public enum MoneyFormat {

    CURRENCY_AMOUNT {

        @Override
        public String toString(final Money<?> money) {
            return money.currency() + " " + money.amount();
        }

    },
    AMOUNT_CURRENCY {

        @Override
        public String toString(final Money<?> money) {
            return money.amount() + " " + money.currency();
        }

    };

    public abstract String toString(Money<?> money);

}
