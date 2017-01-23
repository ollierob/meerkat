package net.meerkat.instrument.interest.swap;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.instrument.derivative.swap.SwapLeg;

/**
 *
 * @author ollie
 */
public class InterestRateSwapLeg implements SwapLeg {

    @XmlElementRef(name = "pay")
    private InterestRateSwapSide pay;

    @XmlElementRef(name = "receive")
    private InterestRateSwapSide receive;

    @Nonnull
    public InterestRateSwapSide pay() {
        return pay;
    }

    @Nonnull
    public InterestRateSwapSide receive() {
        return receive;
    }

}
