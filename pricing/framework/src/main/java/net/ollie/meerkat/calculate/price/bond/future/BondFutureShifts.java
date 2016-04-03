package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondShifts;

/**
 *
 * @author ollie
 */
public interface BondFutureShifts {

    @Nonnull
    BondShifts bondShifts();

    BigDecimal shiftConversionFactor(BigDecimal conversionFactor);

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
