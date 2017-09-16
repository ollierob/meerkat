package net.meerkat.pricing.interest.shifts;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.pricing.shifts.ExchangeRateShifts;
import net.meerkat.pricing.shifts.InterestRateShifts;

/**
 *
 * @author Ollie
 */
public class WrappedInterestRateDerivativeShifts implements InterestRateDerivativeShifts {

    private final InterestRateShifts interestRateShifts;
    private final ExchangeRateShifts exchangeRateShifts;

    public WrappedInterestRateDerivativeShifts(final InterestRateShifts interestRateShifts, final ExchangeRateShifts exchangeRateShifts) {
        this.interestRateShifts = interestRateShifts;
        this.exchangeRateShifts = exchangeRateShifts;
    }

    @Override
    public InterestRate shift(InterestRate rate) {
        return interestRateShifts.shift(rate);
    }

    @Override
    public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(final ExchangeRate<F, T> rate) {
        return exchangeRateShifts.shift(rate);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("interest rate shifts", interestRateShifts)
                .put("exchange rate shifts", exchangeRateShifts);
    }

}
