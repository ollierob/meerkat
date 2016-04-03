package net.ollie.meerkat.calculate.price.bond;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.InterestRateShifts;
import net.ollie.meerkat.calculate.price.shifts.PriceShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.money.fx.ExchangeRate;

/**
 *
 * @author ollie
 */
public interface BondShifts extends PriceShifts, InterestRateShifts, ExchangeRateShifts {

    static BondShifts none() {
        return NoBondShifts.INSTANCE;
    }

    static BondShifts relativePrice(@Nonnull final Percentage percentage) {
        return percentage.isZero() ? none() : new NoBondShifts() {

            @Override
            public <C extends CurrencyId> Money<C> shiftPrice(final Money<C> price) {
                return price.times(percentage);
            }

        };
    }

    class NoBondShifts implements BondShifts {

        static final NoBondShifts INSTANCE = new NoBondShifts();

        @Override
        public <C extends CurrencyId> Money<C> shiftPrice(final Money<C> price) {
            return price;
        }

        @Override
        public FixedInterestRate shift(final FixedInterestRate rate) {
            return rate;
        }

        @Override
        public InterestRate shift(final InterestRate rate) {
            return rate;
        }

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate) {
            return rate;
        }

    }

}
