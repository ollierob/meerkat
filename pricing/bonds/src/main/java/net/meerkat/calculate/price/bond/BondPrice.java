package net.meerkat.calculate.price.bond;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.calculate.price.InstrumentPrice;
import net.meerkat.calculate.price.ShiftableInstrumentPrice;
import net.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.FractionalPercentage;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author ollie
 */
public interface BondPrice<C extends CurrencyId>
        extends InstrumentPrice<C> {

    @Nonnull
    Money<C> par();

    @Override
    Money<C> clean();

    @Override
    Money<C> dirty();

    @Nonnull
    default Percentage cleanPercent() {
        return FractionalPercentage.of(this.par().amount(), this.clean().amount());
    }

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
        return InstrumentPrice.super.explain()
                .put("par", this.par());
    }

    interface Shiftable<C extends CurrencyId>
            extends BondPrice<C>, ShiftableInstrumentPrice<C> {

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
