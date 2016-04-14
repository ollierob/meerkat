package net.ollie.meerkat.security.interest.future;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.derivative.forward.FutureDeliveryDates;
import net.ollie.meerkat.security.interest.InterestRateDerivative;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author Ollie
 */
public class InterestRateFuture
        extends AbstractFuture<InterestRate>
        implements InterestRateDerivative, HasCurrencyId {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "notional")
    private Money<?> notional;

    @XmlElementRef(name = "underlying")
    private InterestRate underlying;

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
    public CurrencyId currencyId() {
        return notional.currencyId();
    }

    @Override
    public InterestRate underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
