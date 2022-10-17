package net.meerkat.money.function;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

public interface MoneyLogarithm extends MoneyFunction {

    double base();

    /**
     * @return {@code log(money^pow) = log(money)*pow}
     */
    default <C extends CurrencyId> Money<C> applyExp(final Money<C> money, final double pow) {
        return this.apply(money).times(pow);
    }

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
