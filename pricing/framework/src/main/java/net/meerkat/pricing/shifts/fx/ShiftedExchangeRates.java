package net.meerkat.pricing.shifts.fx;

import java.util.Objects;
import java.util.Optional;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;

/**
 *
 * @author Ollie
 */
public class ShiftedExchangeRates implements ExchangeRates {

    private final ExchangeRates baseRates;
    private final ExchangeRateShifts shifts;

    public ShiftedExchangeRates(final ExchangeRates baseRates, final ExchangeRateShifts shifts) {
        this.baseRates = Objects.requireNonNull(baseRates);
        this.shifts = Objects.requireNonNull(shifts);
    }

    @Override
    public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> rate(final F from, final T to) throws UnavailableExchangeRate {
        return shifts.shift(baseRates.rate(from, to));
    }

    @Override
    public <F extends CurrencyId, T extends CurrencyId> Optional<ExchangeRate<F, T>> maybeRate(final F from, final T to) {
        return baseRates.maybeRate(from, to).map(shifts::shift);
    }

}
