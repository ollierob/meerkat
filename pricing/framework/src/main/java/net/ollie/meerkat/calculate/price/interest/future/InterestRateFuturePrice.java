package net.ollie.meerkat.calculate.price.interest.future;

import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.calculate.price.InstrumentPrice;
import net.ollie.meerkat.calculate.price.ShiftableInstrumentPrice;

/**
 *
 * @author ollie
 */
public interface InterestRateFuturePrice<C extends CurrencyId>
        extends InstrumentPrice<C> {

    @Nonnull
    Percentage price();

    @Nonnull
    Money<C> multiplier();

    @Nonnull
    default Money<C> value() {
        return this.multiplier().times(this.price());
    }

    @Override
    default Money<C> clean() {
        return this.value();
    }

    @Override
    default Money<C> dirty() {
        return this.value();
    }

    @Override
    default ExplanationBuilder explain() {
        return InstrumentPrice.super.explain()
                .put("price", this.price())
                .put("multiplier", this.multiplier());
    }

    interface Shiftable<C extends CurrencyId>
            extends InterestRateFuturePrice<C>, ShiftableInstrumentPrice<C> {

        @Override
        @Deprecated
        default Optional<ShiftableInstrumentPrice<C>> shift(final SecurityShifts shifts) {
            return shifts.cast(InterestRateFutureShifts.class).map(this::shift);
        }

        @CheckReturnValue
        Shiftable<C> shift(InterestRateFutureShifts shifts);

    }

}
