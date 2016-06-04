package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.date.Interval;
import net.ollie.goat.money.Money;
import net.ollie.goat.numeric.percentage.DecimalPercentage;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.security.fx.CashPayment;

/**
 *
 * @author ollie
 */
public interface BondPrice<C extends Currency>
        extends SecurityPrice<C> {

    @Nonnull
    Money<C> par();

    @Override
    Money<C> clean();

    @Nonnull
    default Percentage cleanPercent() {
        return new DecimalPercentage(this.par().amount().doubleValue() / this.clean().amount().doubleValue());
    }

    @Override
    Money<C> dirty();

    @Nonnull
    default Percentage dirtyPercent() {
        return new DecimalPercentage(this.par().amount().doubleValue() / this.dirty().amount().doubleValue());
    }

    default boolean isPremium() {
        return this.dirty().compareTo(this.par()) > 0;
    }

    @Nonnull
    default Money<C> accruedInterest() {
        return this.dirty().minus(this.clean());
    }

    @Override
    public default ExplanationBuilder explain() {
        return SecurityPrice.super.explain()
                .put("par", this.par());
    }

    interface Shiftable<C extends Currency>
            extends BondPrice<C>, ShiftableSecurityPrice<C> {

        @Override
        default BondPrice.Shiftable<C> shift(final SecurityShifts shifts) {
            return this.shift(shifts.definiteCast(BondShifts.class));
        }

        @CheckReturnValue
        BondPrice.Shiftable<C> shift(BondShifts shifts);

        @Nonnull
        default List<CashPayment<C>> cleanFlow(final Interval interval) {
            return this.cleanFlow(interval.startInclusive(), interval.endExclusive());
        }

        List<CashPayment<C>> cleanFlow(LocalDate starInclusive, LocalDate endExclusive);

    }

}
