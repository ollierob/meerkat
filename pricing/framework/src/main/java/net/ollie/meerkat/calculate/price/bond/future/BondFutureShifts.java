package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.shifts.PriceShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface BondFutureShifts extends PriceShifts {

    /**
     *
     * @param conversionFactor
     * @return
     */
    @Nonnull
    BigDecimal shiftConversionFactor(@Nonnull BigDecimal conversionFactor);

    /**
     *
     * @param <C>
     * @param bondPrice
     * @return
     */
    @Override
    <C extends CurrencyId> Money<C> shift(Money<C> bondPrice);

    /**
     *
     * @return shifts to apply to the cheapest to deliver.
     */
    @Nonnull
    BondShifts bondShifts();

    default <C extends CurrencyId> BondPrice.Shiftable<C> shift(final BondPrice.Shiftable<C> price) {
        return price.shift(this.bondShifts());
    }

    static BondFutureShifts none() {
        return NoBondFutureShifts.INSTANCE;
    }

    class NoBondFutureShifts implements BondFutureShifts {

        static final NoBondFutureShifts INSTANCE = new NoBondFutureShifts();

        @Override
        public BondShifts bondShifts() {
            return BondShifts.none();
        }

        @Override
        public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
            return price;
        }

        @Override
        public BigDecimal shiftConversionFactor(final BigDecimal conversionFactor) {
            return conversionFactor;
        }

    }

}
