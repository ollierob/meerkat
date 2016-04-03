package net.ollie.meerkat.calculate.price.bond;

import java.util.List;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.fx.CashPayment;
import net.ollie.meerkat.time.interim.Interval;

/**
 *
 * @author ollie
 */
public interface BondPrice<C extends CurrencyId> extends SecurityPrice<C> {

    @Nonnull
    Money<C> parValue();

    @Override
    Money<C> cleanValue();

    @Nonnull
    List<CashPayment<C>> cleanFlow(Interval interval);

    @Nonnull
    default Percentage clean() {
        return new Percentage(this.parValue().amount().doubleValue() / this.cleanValue().amount().doubleValue());
    }

    @Override
    default Money<C> dirtyValue() {
        return this.cleanValue().plus(this.accruedInterest());
    }

    @Nonnull
    default Percentage dirty() {
        return new Percentage(this.parValue().amount().doubleValue() / this.dirtyValue().amount().doubleValue());
    }

    default boolean isPremium() {
        return this.dirtyValue().compareTo(this.parValue()) > 0;
    }

    @Nonnull
    Money<C> accruedInterest();

    /**
     * Recalculate a price given some shifts. Should recalculate as little as
     * possible.
     *
     * Shifts do not accumulate.
     *
     * @param shifts
     * @return
     */
    @CheckReturnValue
    BondPrice<C> shift(BondShifts shifts);

}
