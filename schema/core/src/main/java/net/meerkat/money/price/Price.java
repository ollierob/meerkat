package net.meerkat.money.price;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;

import javax.annotation.Nonnull;

/**
 * The price of something. This is sometimes more complicated than just an {@link MoneyPrice value of money}.
 *
 * @author ollie
 * @see MoneyPrice
 */
public interface Price<C extends CurrencyId> extends HasCurrencyId, Explainable {

    @Override
    C currencyId();

    @Nonnull
    Price<C> evaluate();

    interface Evaluated<C extends CurrencyId> extends Price<C> {

        @Override
        @Deprecated
        default Price.Evaluated<C> evaluate() {
            return this;
        }

    }

}
