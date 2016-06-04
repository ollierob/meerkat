package net.ollie.meerkat.numeric.interest.earning;

import java.time.LocalDate;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.ollie.goat.money.interest.InterestRate;
import net.ollie.goat.money.Money;
import net.ollie.goat.money.interest.InterestRateId;

/**
 *
 * @author Ollie
 */
public interface InterestEarning {

    @Nonnull
    Money notional();

    @Nonnull
    InterestRate rate(Function<? super InterestRateId, ? extends InterestRate> getRate);

    @Nonnull
    default Money accrue(final LocalDate start, final LocalDate accrualDate, final Function<InterestRateId, ? extends InterestRate> getRate) {
        return this.rate(getRate).accrue(this.notional(), start, accrualDate);
    }

    @Nonnull
    default Money discount(final LocalDate discountDate, final LocalDate end, final Function<InterestRateId, ? extends InterestRate> getRate) {
        return this.rate(getRate).discount(this.notional(), discountDate, end);
    }

}
