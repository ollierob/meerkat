package net.ollie.meerkat.security.insurance.swap;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.security.derivative.swap.AbstractSwap;
import net.ollie.meerkat.security.insurance.Insurance;
import net.ollie.meerkat.utils.collections.sequence.FiniteSequence;

/**
 *
 * @author Ollie
 */
public class CreditDefaultSwap
        extends AbstractSwap {

    @XmlElementRef(name = "insurance")
    private Insurance insurance;

    @XmlElement(name = "dates")
    private CreditDefaultSwapDates dates;

    @XmlElement(name = "leg")
    private List<CreditDefaultSwapLeg> legs;

    @Deprecated
    CreditDefaultSwap() {
    }

    @Override
    public FiniteSequence<CreditDefaultSwapLeg> legs() {
        return FiniteSequence.of(legs);
    }

    public CreditDefaultSwapDates dates() {
        return dates;
    }

}
