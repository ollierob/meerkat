package net.meerkat.pricing.shifts;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.suppliers.Suppliers;

/**
 *
 * @author Ollie
 */
public class WrappedSecurityShifts implements PriceShifts, InterestRateShifts, ExchangeRateShifts {

    private final PriceShifts priceShifts;
    private final InterestRateShifts interestRateShifts;
    private final ExchangeRateShifts exchangeRateShifts;

    public WrappedSecurityShifts(final SecurityShifts shifts) {
        this(PriceShifts.cast(shifts), InterestRateShifts.cast(shifts), ExchangeRateShifts.cast(shifts));
    }

    public WrappedSecurityShifts(
            final PriceShifts priceShifts,
            final InterestRateShifts interestRateShifts,
            final ExchangeRateShifts exchangeRateShifts) {
        this.priceShifts = Suppliers.firstNonNull(priceShifts, PriceShifts.none());
        this.interestRateShifts = Suppliers.firstNonNull(interestRateShifts, InterestRateShifts.none());
        this.exchangeRateShifts = Suppliers.firstNonNull(exchangeRateShifts, ExchangeRateShifts.none());
    }

    @Override
    public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
        return priceShifts.shift(price);
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
                .put("price shifts", priceShifts)
                .put("interest rate shifts", interestRateShifts)
                .put("exchange rate shifts", exchangeRateShifts);
    }

}
