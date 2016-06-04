package net.ollie.meerkat.security.interest.future;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import org.apache.commons.math3.fraction.Fraction;

import net.ollie.goat.currency.Currency;
import net.ollie.goat.currency.HasCurrency;
import net.ollie.goat.money.Money;
import net.ollie.meerkat.numeric.interest.InterestRateSecurity;
import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.derivative.forward.FutureDeliveryDates;
import net.ollie.meerkat.security.interest.InterestRateDerivative;

/**
 *
 * @author Ollie
 */
public class InterestRateFuture
        extends AbstractFuture<InterestRateSecurity>
        implements InterestRateDerivative, HasCurrency {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "notional")
    private Money<?> notional;

    @XmlElementRef(name = "underlying")
    private InterestRateSecurity underlying;

    @XmlElementRef(name = "dates")
    private FutureDeliveryDates dates;

    @XmlAttribute(name = "yearly_frequency")
    private int yearlyFrequency;

    @Deprecated
    InterestRateFuture() {
    }

    @Override
    public FutureDeliveryDates dates() {
        return dates;
    }

    public Money<?> notional() {
        return notional;
    }

    public Fraction accrualFactor() {
        return new Fraction(yearlyFrequency, 12);
    }

    @Override
    public Currency currency() {
        return notional.currency();
    }

    @Override
    public InterestRateSecurity underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
