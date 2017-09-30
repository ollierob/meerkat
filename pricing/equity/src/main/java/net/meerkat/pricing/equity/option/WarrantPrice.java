package net.meerkat.pricing.equity.option;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author Ollie
 */
public interface WarrantPrice<C extends CurrencyId> extends Price.Valued<C> {

    @Nonnull
    Money<C> intrinsicValue();

    /**
     * @return the "uncertainty" premium.
     */
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

    interface Shiftable<C extends CurrencyId> extends WarrantPrice<C>, ShiftablePrice<C> {

        @Override
        WarrantPrice.Shiftable<C> shift(InstrumentShifts shifts);

    }

}
