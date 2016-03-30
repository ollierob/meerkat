package net.ollie.meerkat.calculate.price.bond;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.fx.CashPayment;
import net.ollie.meerkat.time.interim.Interval;
import net.ollie.meerkat.utils.collections.Sequence;

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
    Sequence<CashPayment<C>> cleanFlow(Interval interval);

    @Nonnull
    default Percentage clean() {
        return new Percentage(this.parValue().amount().doubleValue() / this.cleanValue().amount().doubleValue());
    }

    @Override
    Money<C> dirtyValue();

    @Nonnull
    default Percentage dirty() {
        return new Percentage(this.parValue().amount().doubleValue() / this.dirtyValue().amount().doubleValue());
    }

    default boolean isPremium() {
        return this.dirtyValue().compareTo(this.parValue()) > 0;
    }

    @Nonnull
    default Money<C> accrued() {
        return this.dirtyValue().minus(this.cleanValue());
    }

}
