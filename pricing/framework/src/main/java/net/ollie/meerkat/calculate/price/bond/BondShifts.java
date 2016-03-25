package net.ollie.meerkat.calculate.price.bond;

import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.InterestRateShifts;
import net.ollie.meerkat.calculate.price.shifts.PriceShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.ExchangeRate;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface BondShifts extends PriceShifts, InterestRateShifts, ExchangeRateShifts {

    BondShifts NONE = new BondShifts() {

        @Override
        public <C extends CurrencyId> Money<C> shiftPrice(final Money<C> price) {
            return price;
        }

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shiftExchangeRate(final ExchangeRate<F, T> rate) {
            return rate;
        }

        @Override
        public FixedInterestRate shiftRates(final FixedInterestRate rate) {
            return rate;
        }

    };

}
