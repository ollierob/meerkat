package net.meerkat.money;

import net.meerkat.identifier.currency.CurrencyId;

public interface MoneyFunction {

    <C extends CurrencyId> Money<C> apply(Money<C> money);

}
