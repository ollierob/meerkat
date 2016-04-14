package net.ollie.meerkat.calculate.price.interest.future;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface InterestRateFuturePrice<C extends CurrencyId>
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
    default Money<C> cleanValue() {
        return this.value();
    }

    @Override
    default Money<C> dirtyValue() {
        return this.value();
    }

    interface Shiftable<C extends CurrencyId>
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
