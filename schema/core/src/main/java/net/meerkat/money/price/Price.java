package net.meerkat.money.price;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface Price<C extends CurrencyId> extends HasCurrencyId, Explainable {

    @Override
    C currencyId();

    interface Valued<C extends CurrencyId> extends Price<C> {

        @Nonnull
        Money<C> value();

        @Override
        default C currencyId() {
            return this.value().currencyId();
        }

    }

}
