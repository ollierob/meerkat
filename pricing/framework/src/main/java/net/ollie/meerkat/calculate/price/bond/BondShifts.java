package net.ollie.meerkat.calculate.price.bond;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.InterestRateShifts;
import net.ollie.meerkat.calculate.price.shifts.PriceShifts;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface BondShifts extends PriceShifts, InterestRateShifts, ExchangeRateShifts {

    /**
     *
     * @param <C> currency
     * @param marketPrice
     * @return shifted market price.
     */
    @Override
    <C extends CurrencyId> Money<C> shift(Money<C> marketPrice);

    /**
     *
     * @param <F> source currency
     * @param <T> target currency
     * @param rate
     * @return shifted FX rate
     */
    @Override
    <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate);

    static BondShifts none() {
        return NoBondShifts.INSTANCE;
    }

    static BondShifts relativePrice(@Nonnull final Percentage percentage) {
        return percentage.isZero() ? none() : new NoBondShifts() {

            @Override
            public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
                return price.times(percentage);
            }

        };
    }

    class NoBondShifts implements BondShifts {

        static final NoBondShifts INSTANCE = new NoBondShifts();

        @Override
        public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
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

        @Override
        public Map<String, Object> explain() {
            return Collections.emptyMap();
        }

    }

}
