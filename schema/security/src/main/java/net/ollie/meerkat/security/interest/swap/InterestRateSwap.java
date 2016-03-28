package net.ollie.meerkat.security.interest.swap;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.security.derivative.swap.AbstractSwap;
import net.ollie.meerkat.security.interest.InterestRateDerivative;
import net.ollie.meerkat.utils.collections.FiniteSequence;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class InterestRateSwap
        extends AbstractSwap
        implements InterestRateDerivative {

    @XmlAttribute(name = "trade_date")
    private LocalDate tradeDate;

    @XmlElement(name = "leg")
    private List<InterestRateSwapLeg> legs;

    @Deprecated
    InterestRateSwap() {
    }

    public InterestRateSwap(
            final String name,
            final LocalDate tradeDate) {
        super(name);
        this.tradeDate = tradeDate;
    }

    @Override
    public FiniteSequence<InterestRateSwapLeg> legs() {
        return FiniteSequence.of(legs);
    }

    public int numLegs() {
        return legs.size();
    }

    public InterestRateSwapSide payLeg(final int index) {
        return legs.get(index).pay();
    }

    public InterestRateSwapSide receiveLeg(final int index) {
        return legs.get(index).receive();
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
