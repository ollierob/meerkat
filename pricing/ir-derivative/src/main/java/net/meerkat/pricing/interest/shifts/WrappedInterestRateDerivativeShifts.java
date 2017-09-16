package net.meerkat.pricing.interest.shifts;

import java.util.Map;

import static net.coljate.util.Suppliers.firstNonNull;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;
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
        this.interestRateShifts = firstNonNull(interestRateShifts, InterestRateShifts.none());
        this.exchangeRateShifts = firstNonNull(exchangeRateShifts, ExchangeRateShifts.none());
    }

    @Override
    public InterestRate shift(final InterestRate rate) {
        return interestRateShifts.shift(rate);
    }

    @Override
    public ExchangeRates shift(final ExchangeRates rates) {
        return exchangeRateShifts.shift(rates);
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
