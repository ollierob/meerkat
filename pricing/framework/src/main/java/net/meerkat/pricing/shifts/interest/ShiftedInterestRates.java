package net.meerkat.pricing.shifts.interest;

import net.meerkat.functions.Functions;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.InterestRateSnapshot;
import net.ollie.goat.data.Element;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public class ShiftedInterestRates implements InterestRateSnapshot {

    private final InterestRateSnapshot rates;
    private final InterestRateShifts shifts;

    public ShiftedInterestRates(final InterestRateSnapshot rates, final InterestRateShifts shifts) {
        this.rates = Objects.requireNonNull(rates);
        this.shifts = Objects.requireNonNull(shifts);
    }

    @Override
    public InterestRate discountRate(final CurrencyId currencyId) {
        return shifts.shift(rates.discountRate(currencyId));
    }

    @Override
    public InterestRate get(final InterestRateId id) {
        return Functions.ifNonNull(rates.get(id), shifts::shift);
    }

    @Override
    public Element<InterestRate> getElement(final InterestRateId key) {
        return Functions.ifNonNull(this.get(key), Element::ofNullable);
    }

}
