package net.ollie.meerkat.numeric.interest;

import java.math.MathContext;
import java.time.LocalDate;

import net.ollie.goat.date.Dates;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author Ollie
 */
public interface FloatingInterestRate extends InterestRate {

    @Override
    default <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate end) {
        final Percentage impliedForwardRate = this.impliedForwardRate(start, end);
        return this.accrue(money, impliedForwardRate, start, end);
    }

    default Percentage impliedForwardRate(final LocalDate start, final LocalDate end) {
        if (Dates.equals(this.spot(), start)) {
            return this.fixing(end);
        }
        final AccrualFactor accrual = this.accrual();
        final LocalDate spot = this.spot();
        final Years d1 = accrual.yearsBetween(spot, start);
        final Percentage r1 = this.rateOver(d1);
        final Years d2 = accrual.yearsBetween(spot, start);
        final Percentage r2 = this.rateOver(d2);
        final Years term = accrual.yearsBetween(start, end);
        return (r2.timesBy(d2).minus(r1.timesBy(d1))).over(term.decimalValue(), MathContext.DECIMAL128);
    }

    LocalDate spot();

    <C extends CurrencyId> Money<C> accrue(Money<C> money, Percentage forwardRate, LocalDate start, LocalDate end);

    Percentage rateOver(Years term);

}
