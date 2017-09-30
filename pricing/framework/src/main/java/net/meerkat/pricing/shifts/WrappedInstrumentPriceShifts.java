package net.meerkat.pricing.shifts;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.suppliers.Suppliers;

/**
 *
 * @author Ollie
 */
public class WrappedInstrumentPriceShifts implements InterestRateShifts, ExchangeRateShifts {

    private final InterestRateShifts interestRateShifts;
    private final ExchangeRateShifts exchangeRateShifts;

    public WrappedInstrumentPriceShifts(final InstrumentPriceShifts shifts) {
        this(InterestRateShifts.cast(shifts), ExchangeRateShifts.cast(shifts));
    }

    public WrappedInstrumentPriceShifts(
            final InterestRateShifts interestRateShifts,
            final ExchangeRateShifts exchangeRateShifts) {
        this.interestRateShifts = Suppliers.firstNonNull(interestRateShifts, InterestRateShifts.none());
        this.exchangeRateShifts = Suppliers.firstNonNull(exchangeRateShifts, ExchangeRateShifts.none());
    }

    @Override
    public InterestRate shift(final InterestRate rate) {
        return interestRateShifts.shift(rate);
    }

    @Override
    public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate) {
        return exchangeRateShifts.shift(rate);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("interest rate shifts", interestRateShifts)
                .put("exchange rate shifts", exchangeRateShifts);
    }

}
