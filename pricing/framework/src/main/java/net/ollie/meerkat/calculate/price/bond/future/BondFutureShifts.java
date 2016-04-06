package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.fx.ExchangeRate;

/**
 *
 * @author ollie
 */
public interface BondFutureShifts extends SecurityShifts, ExchangeRateShifts {

    @Nonnull
    BondShifts bondShifts();

    @Nonnull
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

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate) {
            return rate;
        }

    }

}
