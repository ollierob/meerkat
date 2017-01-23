package net.meerkat.instrument.interest.swap;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.swap.AbstractSwap;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.issuer.IssuerId;
import net.ollie.meerkat.utils.collections.sequence.FiniteSequence;

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
            final InstrumentIds identifiers,
            final IssuerId issuer,
            final LocalDate tradeDate) {
        super(name, identifiers, issuer);
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
