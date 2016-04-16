package net.ollie.meerkat.numeric.interest;

import java.math.MathContext;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlTransient;

import net.ollie.goat.date.Dates;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.utils.numeric.Percentage;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author Ollie
 */
@XmlTransient
public abstract class FloatingInterestRate implements InterestRate {

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate end) {
        final Percentage impliedForwardRate = this.impliedForwardRate(start, end);
        return this.accrue(money, impliedForwardRate, start, end);
    }

    public Percentage impliedForwardRate(final LocalDate start, final LocalDate end) {
        if (Dates.equals(this.spot(), start)) {
            return this.fixing(end);
        }
        final Years d1 = this.term(this.spot(), start);
        final Percentage r1 = this.rateOver(d1);
        final Years d2 = this.term(this.spot(), end);
        final Percentage r2 = this.rateOver(d2);
        final Years term = this.term(start, end);
        return (r2.times(d2.decimalValue()).minus(r1.times(d2.decimalValue()))).over(term.decimalValue(), MathContext.DECIMAL128);
    }

    public abstract LocalDate spot();

    protected abstract <C extends CurrencyId> Money<C> accrue(Money<C> money, Percentage forwardRate, LocalDate start, LocalDate end);

    public abstract Percentage rateOver(Years term);

    protected final Years term(final LocalDate start, final LocalDate end) {
        return this.accrual().yearsBetween(start, end);
    }

    @Override
    public Percentage fixing(final LocalDate date) {
        return this.rateOver(this.term(this.spot(), date));
    }

}
