package net.meerkat.money.function;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

public interface MoneyFunction {

    <C extends CurrencyId> Money<C> apply(Money<C> money);

    default MoneyFunction thenApply(final MoneyFunction that) {
        return new MoneyFunction() {

            @Override
            public <C extends CurrencyId> Money<C> apply(final Money<C> money) {
                final var m1 = MoneyFunction.this.apply(money);
                return m1 != null ? that.apply(m1) : null;
            }

        };
    }

}
