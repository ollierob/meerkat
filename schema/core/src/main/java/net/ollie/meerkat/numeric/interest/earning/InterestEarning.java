package net.ollie.meerkat.numeric.interest.earning;

import java.time.LocalDate;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.interest.InterestRateKey;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
public interface InterestEarning {

    @Nonnull
    Money notional();

    @Nonnull
    InterestRate rate(Function<? super InterestRateKey, ? extends InterestRate> getRate);

    @Nonnull
    default Money accrue(final LocalDate start, final LocalDate accrualDate, final Function<InterestRateKey, ? extends InterestRate> getRate) {
        return this.rate(getRate).accrue(this.notional(), start, accrualDate);
    }

    @Nonnull
    default Money discount(final LocalDate discountDate, final LocalDate end, final Function<InterestRateKey, ? extends InterestRate> getRate) {
        return this.rate(getRate).discount(this.notional(), discountDate, end);
    }

}
