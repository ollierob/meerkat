package net.meerkat.money;

import net.meerkat.identifier.currency.CurrencyId;

public interface MoneyLogarithm extends MoneyFunction {

    double base();

    MoneyLogarithm LN = new MoneyLogarithm() {

        @Override
        public double base() {
            return Math.E;
        }

        @Override
        public <C extends CurrencyId> Money<C> apply(final Money<C> money) {
            return Money.of(money.currencyId(), Math.log(money.doubleValue()));
        }

    };

}
