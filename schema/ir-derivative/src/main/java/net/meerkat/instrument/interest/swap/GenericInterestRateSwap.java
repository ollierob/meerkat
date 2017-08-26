package net.meerkat.instrument.interest.swap;

import net.coljate.list.List;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class GenericInterestRateSwap
        extends NamedInstrument
        implements InterestRateSwap {

    private static final long serialVersionUID = 1L;

    private final List<InterestRateSwapLeg<?, ?>> legs;
    private final IssuerId issuerId;

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
    public List<InterestRateSwapLeg<?, ?>> legs() {
        return legs;
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
