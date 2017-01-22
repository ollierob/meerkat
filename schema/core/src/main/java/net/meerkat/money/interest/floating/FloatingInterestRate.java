package net.meerkat.money.interest.floating;

import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public abstract class FloatingInterestRate implements InterestRate {

    @XmlAttribute(name = "date", required = true)
    private LocalDate referenceDate;

    @XmlElementRef(name = "dates")
    private DateArithmetic dates;

    @Deprecated
    protected FloatingInterestRate() {
    }

    protected FloatingInterestRate(final LocalDate referenceDate, final DateArithmetic dates) {
        this.referenceDate = referenceDate;
        this.dates = dates;
    }

    @Nonnull
    public LocalDate referenceDate() {
        return referenceDate;
    }

    @Override
    public DateArithmetic dateArithmetic() {
        return dates;
    }

    protected Years yearsUntil(final LocalDate date) {
        return dates.yearsBetween(referenceDate, date);
    }

    @Override
    public Percentage spot(final LocalDate end) {
        return this.forward(this.referenceDate(), end);
    }

    @Override
    public <C extends Currency> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate end) {
        final Percentage impliedForwardRate = this.forward(start, end);
        return this.accrue(money, impliedForwardRate, start, end);
    }

    protected abstract <C extends Currency> Money<C> accrue(Money<C> money, Percentage forwardRate, LocalDate start, LocalDate end);

    protected final Years term(final LocalDate start, final LocalDate end) {
        return this.dateArithmetic().yearsBetween(start, end);
    }

}
