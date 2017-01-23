package net.meerkat.instrument.insurance.swap;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

import net.meerkat.instrument.Matures;

/**
 *
 * @author Ollie
 */
public class CreditDefaultSwapDates implements Matures {

    @XmlAttribute(name = "trade")
    private LocalDate trade;

    @XmlAttribute(name = "step_in")
    private LocalDate stepIn;

    @XmlAttribute(name = "valuation")
    private LocalDate valuation;

    @XmlAttribute(name = "cash_settle")
    private LocalDate cashSettle;

    @XmlAttribute(name = "maturity")
    private LocalDate maturity;

    @Override
    public LocalDate matures() {
        return maturity;
    }

}
