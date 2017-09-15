package net.meerkat.instrument.interest.swap;

import net.coljate.list.List;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;
import net.meerkat.issue.Issue;

/**
 *
 * @author ollie
 */
public class GenericInterestRateSwap
        extends IssuedSecurity
        implements InterestRateSwap {

    private final List<InterestRateSwapLeg<?, ?>> legs;

    public GenericInterestRateSwap(
            final String name,
            final InstrumentIds ids,
            final List<InterestRateSwapLeg<?, ?>> legs,
            final Issue issue) {
        super(name, ids, issue);
        this.legs = legs;
    }

    @Override
    public List<InterestRateSwapLeg<?, ?>> legs() {
        return legs;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("legs", legs);
    }

}
