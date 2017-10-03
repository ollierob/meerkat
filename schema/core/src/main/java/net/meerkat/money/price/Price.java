package net.meerkat.money.price;

import javax.annotation.CheckReturnValue;
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

    @Nonnull
    Price<C> evaluate();

    interface Evaluated<C extends CurrencyId> extends Price<C> {

        @Deprecated
        default Price<C> evaluate() {
            return this;
        }

    }

    interface Valued<C extends CurrencyId> extends Price<C> {

        @Nonnull
        Money<C> value();

        @CheckReturnValue
        @Nonnull
        default TwoWayMoney<C> plusSpread(@Nonnull final Money<C> spread) {
            return new SpreadMoney<>(this.value(), spread.over(2));
        }

        @Override
        default EvaluatedPrice<C> evaluate() {
            return new EvaluatedPrice<>(this.value());
        }

        @Override
        default C currencyId() {
            return this.value().currencyId();
        }

    }

}
