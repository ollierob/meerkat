package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

public interface MoneyPrice<C extends CurrencyId> extends Price<C> {

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
