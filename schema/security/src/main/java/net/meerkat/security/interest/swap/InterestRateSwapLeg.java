package net.meerkat.security.interest.swap;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.security.derivative.swap.SwapLeg;

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
