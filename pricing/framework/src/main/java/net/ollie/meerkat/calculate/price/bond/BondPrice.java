package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.DecimalPercentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.fx.CashPayment;
import net.ollie.meerkat.time.interim.Interval;
import net.ollie.meerkat.utils.numeric.Percentage;

/**
 *
 * @author ollie
 */
public interface BondPrice<C extends CurrencyId>
        extends SecurityPrice<C>, HasCurrencyId {

    @Nonnull
    Money<C> parValue();

    @Override
    Money<C> cleanValue();

    @Nonnull
    default Percentage clean() {
        return new DecimalPercentage(this.parValue().amount().doubleValue() / this.cleanValue().amount().doubleValue());
    }

    @Override
    Money<C> dirtyValue();

    @Nonnull
    default Percentage dirty() {
        return new DecimalPercentage(this.parValue().amount().doubleValue() / this.dirtyValue().amount().doubleValue());
    }

    default boolean isPremium() {
        return this.dirtyValue().compareTo(this.parValue()) > 0;
    }

    @Nonnull
    default Money<C> accruedInterest() {
        return this.dirtyValue().minus(this.cleanValue());
    }

    @Override
    default C currencyId() {
        return this.cleanValue().currencyId();
    }

    interface Shiftable<C extends CurrencyId>
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
