package net.meerkat.numeric.interest.earning;

import java.time.LocalDate;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.numeric.interest.InterestRateId;

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
    default Money<?> accrue(final LocalDate start, final LocalDate accrualDate, final Function<InterestRateId, ? extends InterestRate> getRate) {
        return this.rate(getRate).accrue(this.notional(), start, accrualDate);
    }

    @Nonnull
    default Money<?> discount(final LocalDate discountDate, final LocalDate end, final Function<InterestRateId, ? extends InterestRate> getRate) {
        return this.rate(getRate).discount(this.notional(), discountDate, end);
    }

    interface Fixed extends InterestEarning {

        FixedInterestRate rate();

        @Override
        @Deprecated
        default InterestRate rate(Function<? super InterestRateId, ? extends InterestRate> getRate) {
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
        default Money<?> discount(LocalDate discountDate, LocalDate end, Function<InterestRateId, ? extends InterestRate> getRate) {
            return this.discount(discountDate, end);
        }

        @Override
        @Deprecated
        default Money<?> accrue(LocalDate start, LocalDate accrualDate, Function<InterestRateId, ? extends InterestRate> getRate) {
            return this.accrue(start, accrualDate);
        }

    }

}
