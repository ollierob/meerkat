package net.meerkat.instrument.bond.call;

import net.meerkat.numeric.percentage.Percentage;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public class StandardBondCall implements BondCall {

    @XmlAttribute(name = "earliest")
    private LocalDate earliest;

    @XmlAttribute(name = "premium")
    private Percentage premium;

    @Override
    public LocalDate earliest() {
        return earliest;
    }

    @Override
    public Percentage premium() {
        return premium;
    }

}
