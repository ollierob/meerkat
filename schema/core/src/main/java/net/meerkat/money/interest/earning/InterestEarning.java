package net.meerkat.money.interest.earning;

import java.time.LocalDate;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;

/**
 *
 * @author Ollie
 */
public interface InterestEarning {

    @Nonnull
    Money<?> notional();

    @Nonnull
    InterestRate rate(Function<? super InterestRateId, ? extends InterestRate> getRate);

    @Nonnull
    default Money<?> accrue(
            final LocalDate start,
            final LocalDate accrualDate,
            final Function<InterestRateId, ? extends InterestRate> getRate,
            final InterestRateInterpolator interpolator) {
        return this.rate(getRate).accrue(this.notional(), start, accrualDate, interpolator);
    }

    @Nonnull
    default Money<?> discount(
            final LocalDate discountDate,
            final LocalDate end,
            final Function<InterestRateId, ? extends InterestRate> getRate,
            final InterestRateInterpolator interpolator) {
        return this.rate(getRate).discount(this.notional(), discountDate, end, interpolator);
    }


}
