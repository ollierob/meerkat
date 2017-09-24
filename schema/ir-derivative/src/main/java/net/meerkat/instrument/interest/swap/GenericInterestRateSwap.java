package net.meerkat.instrument.interest.swap;

import net.coljate.list.List;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class GenericInterestRateSwap
        extends IssuedSecurity
        implements InterestRateSwap {

    private final List<? extends InterestRateSwapLeg<?, ?>> legs;

    public GenericInterestRateSwap(
            final String name,
            final InstrumentIds ids,
            final IssuerId issue,
            final List<? extends InterestRateSwapLeg<?, ?>> legs) {
        super(name, ids, issue);
        this.legs = legs;
    }

    @Override
    public List<? extends InterestRateSwapLeg<?, ?>> legs() {
        return legs;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("legs", legs);
    }

}
