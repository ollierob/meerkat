package net.ollie.meerkat.calculate.price.bond.future;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface BondFuturePrice<C extends CurrencyId>
        extends SecurityPrice<C> {

    /**
     *
     * @return shifted cheapest to deliver.
     */
    @Nonnull
    CheapestToDeliver<C> cheapestToDeliver();

    @Override
    default EvaluatedBondFuturePrice<C> evaluate() {
        return new EvaluatedBondFuturePrice<>(this.cleanValue(), this.dirtyValue(), this.cheapestToDeliver());
    }

    interface Shiftable<C extends CurrencyId>
            extends BondFuturePrice<C>, ShiftableSecurityPrice<C> {

        @Override
        @Deprecated
        default BondFuturePrice.Shiftable<C> shift(final SecurityShifts shifts) {
            return this.shift(shifts.definiteCast(BondFutureShifts.class));
        }

        @CheckReturnValue
        BondFuturePrice.Shiftable<C> shift(BondFutureShifts shifts);

    }

}
