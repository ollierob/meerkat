package net.meerkat.calculate.price.bond;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.meerkat.calculate.price.shifts.InterestRateShifts;
import net.meerkat.calculate.price.shifts.PriceShifts;
import net.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.numeric.percentage.Percentage;

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
            public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
                return price.times(percentage);
            }

        };
    }

    static BondShifts cast(final SecurityShifts shifts) {
        return shifts instanceof BondShifts
                ? ((BondShifts) shifts)
                : new BridgedBondShifts(shifts);
    }

    class NoBondShifts implements BondShifts {

        static final NoBondShifts INSTANCE = new NoBondShifts();

        @Override
        public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
            return price;
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

    class BridgedBondShifts implements BondShifts {

        private final SecurityShifts shifts;

        public BridgedBondShifts(final SecurityShifts shifts) {
            this.shifts = shifts;
        }

        @Override
        public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
            return shifts instanceof PriceShifts
                    ? ((PriceShifts) shifts).shift(price)
                    : price;
        }

        @Override
        public InterestRate shift(final InterestRate rate) {
            return shifts instanceof InterestRateShifts
                    ? ((InterestRateShifts) shifts).shift(rate)
                    : rate;
        }

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate) {
            return shifts instanceof ExchangeRateShifts
                    ? ((ExchangeRateShifts) shifts).shift(rate)
                    : rate;
        }

        @Override
        public Map<String, Object> explain() {
            return shifts.explain();
        }

    }

}
