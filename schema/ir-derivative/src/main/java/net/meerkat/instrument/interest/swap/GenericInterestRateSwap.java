package net.meerkat.instrument.interest.swap;

import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.issuer.IssuerId;
import net.meerkat.utils.collections.sequence.FiniteSequence;
import net.meerkat.utils.collections.sequence.Sequence;

/**
 *
 * @author ollie
 */
public class GenericInterestRateSwap
        extends NamedInstrument
        implements InterestRateSwap {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "leg")
    private List<InterestRateSwapLeg<?, ?>> legs;

    @XmlElementRef(name = "issuer")
    private IssuerId issuerId;

    public GenericInterestRateSwap(
            final String name,
            final InstrumentIds ids,
            final List<InterestRateSwapLeg<?, ?>> legs,
            final IssuerId issuerId) {
        super(name, ids);
        this.legs = legs;
        this.issuerId = issuerId;
    }

    @Override
    public FiniteSequence<InterestRateSwapLeg<?, ?>> legs() {
        return Sequence.of(legs);
    }

    @Override
    public IssuerId issuerId() {
        return issuerId;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("issuer", issuerId)
                .put("legs", legs);
    }

}
