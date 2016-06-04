package net.ollie.meerkat.calculate.price.interest.future;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface InterestRateFuturePrice<C extends Currency>
        extends SecurityPrice<C> {

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
        return SecurityPrice.super.explain()
                .put("price", this.price())
                .put("multiplier", this.multiplier());
    }

    interface Shiftable<C extends Currency>
            extends InterestRateFuturePrice<C>, ShiftableSecurityPrice<C> {

        @CheckReturnValue
        Shiftable<C> shift(InterestRateFutureShifts shifts);

        @Override
        @Deprecated
        default ShiftableSecurityPrice<C> shift(final SecurityShifts shifts) throws InvalidShiftTypeException {
            return this.shift(shifts.definiteCast(InterestRateFutureShifts.class));
        }

    }

}
