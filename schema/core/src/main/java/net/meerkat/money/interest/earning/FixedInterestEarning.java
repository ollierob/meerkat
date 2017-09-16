package net.meerkat.money.interest.earning;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.money.interest.InterestRates;

/**
 *
 * @author ollie
 */
public interface FixedInterestEarning extends InterestEarning {

    @Nonnull
    FixedInterestRate rate();

    @Override
    @Deprecated
    default InterestRate rate(final InterestRates getRate) {
        return this.rate();
    }

    default Money<?> accrue(final LocalDate since, final LocalDate until) {
        return this.rate().accrue(this.notional(), since, until);
    }

    default Money<?> discount(final LocalDate discountDate, final LocalDate end) {
        return this.rate().discount(this.notional(), discountDate, end);
    }

    @Override
    @Deprecated
    default Money<?> discount(
            final LocalDate discountDate,
            final LocalDate end,
            final InterestRates getRate,
            final InterestRateInterpolator interpolator) {
        return this.discount(discountDate, end);
    }

    @Override
    @Deprecated
    default Money<?> accrue(
            final LocalDate start,
            final LocalDate accrualDate,
            final InterestRates getRate,
            final InterestRateInterpolator interpolator) {
        return this.accrue(start, accrualDate);
    }

}
