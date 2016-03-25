package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
public class FloatingSpreadInterestRate implements FloatingInterestRate {

    @XmlElementRef(name = "underlying")
    private FloatingInterestRate underlying;

    @XmlAttribute(name = "spread")
    private Percentage spread;

    FloatingSpreadInterestRate() {
    }

    public FloatingSpreadInterestRate(final FloatingInterestRate underlying, final Percentage spread) {
        this.underlying = underlying;
        this.spread = spread;
    }

    @Override
    public Percentage fixing(final LocalDate date) {
        return underlying.fixing(date).plus(spread);
    }

    @Override
    public Money accrue(final Money money, final LocalDate start, final LocalDate accrualDate) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public FloatingSpreadInterestRate plus(final Percentage bump) {
        return new FloatingSpreadInterestRate(underlying, spread.plus(bump));
    }

    @Override
    public String name() {
        return underlying.name() + "+" + spread;
    }

}
