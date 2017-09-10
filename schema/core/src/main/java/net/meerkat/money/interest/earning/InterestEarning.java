package net.meerkat.money.interest.earning;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateProvider;
import net.meerkat.money.interest.UnknownInterestRateException;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;

/**
 *
 * @author Ollie
 */
public interface InterestEarning {

    @Nonnull
    Money<?> notional();

    @Nonnull
    InterestRate rate(InterestRateProvider rates) throws UnknownInterestRateException;

    @Nonnull
    default Money<?> accrue(
            final LocalDate start,
            final LocalDate accrualDate,
            final InterestRateProvider rates,
            final InterestRateInterpolator interpolator) {
        return this.rate(rates).accrue(this.notional(), start, accrualDate, interpolator);
    }

    @Nonnull
    default Money<?> discount(
            final LocalDate discountDate,
            final LocalDate end,
            final InterestRateProvider rates,
            final InterestRateInterpolator interpolator) {
        return this.rate(rates).discount(this.notional(), discountDate, end, interpolator);
    }

}
