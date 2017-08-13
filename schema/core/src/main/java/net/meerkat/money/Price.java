package net.meerkat.money;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;

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
