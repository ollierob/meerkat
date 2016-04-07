package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface BondFutureShifts extends SecurityShifts {

    @Nonnull
    BigDecimal shiftConversionFactor(BigDecimal conversionFactor);

    @Nonnull
    BondShifts bondShifts();

    default <C extends CurrencyId> BondPrice<C> shift(final BondPrice<C> price) {
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
        public BigDecimal shiftConversionFactor(final BigDecimal conversionFactor) {
            return conversionFactor;
        }

    }

}
