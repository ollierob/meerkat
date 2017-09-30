package net.meerkat.pricing.equity.option;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;

/**
 *
 * @author Ollie
 */
public interface WarrantPrice<C extends CurrencyId> extends Price.Valued<C> {

    @Nonnull
    Money<C> intrinsicValue();

    @Nonnull
    Money<C> timeValue();

    @Override
    default Money<C> value() {
        return this.intrinsicValue().plus(this.timeValue());
    }

    @Override
    default EvaluatedWarrantPrice<C> evaluate() {
        return new EvaluatedWarrantPrice<>(this.intrinsicValue(), this.value());
    }

}
