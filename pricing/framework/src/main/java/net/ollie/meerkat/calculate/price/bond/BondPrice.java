package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.ollie.goat.numeric.percentage.FractionalPercentage;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.instrument.CashPayment;

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
        return FractionalPercentage.of(this.par().amount(), this.clean().amount());
    }

    @Override
    Money<C> dirty();

    @Nonnull
    default Percentage dirtyPercent() {
        return FractionalPercentage.of(this.par().amount(), this.dirty().amount());
    }

    default boolean isPremium() {
        return this.dirty().compareTo(this.par()) > 0;
    }

    @Nonnull
    default Money<C> accruedInterest() {
        return this.dirty().minus(this.clean());
    }

    @Nonnull
    Percentage yieldToMaturity();

    @Override
    public default ExplanationBuilder explain() {
        return SecurityPrice.super.explain()
                .put("par", this.par());
    }

    interface Shiftable<C extends Currency>
            extends BondPrice<C>, ShiftableSecurityPrice<C> {

        @Override
        @Deprecated
        default Optional<BondPrice.Shiftable<C>> shift(final SecurityShifts shifts) {
            return shifts.cast(BondShifts.class).map(this::shift);
        }

        @CheckReturnValue
        BondPrice.Shiftable<C> shift(BondShifts shifts);

        @Nonnull
        default List<CashPayment<C>> cleanFlow(final CompleteInterval interval) {
            return this.cleanFlow(interval.startInclusive(), interval.endExclusive());
        }

        List<CashPayment<C>> cleanFlow(LocalDate starInclusive, LocalDate endExclusive);

    }

}
