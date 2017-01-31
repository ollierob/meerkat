package net.meerkat.instrument.insurance.swap;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.instrument.derivative.swap.AbstractSwap;
import net.meerkat.instrument.insurance.Insurance;
import net.meerkat.utils.collections.sequence.FiniteSequence;

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