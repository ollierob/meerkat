package net.meerkat.instrument.bond.call;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.goat.numeric.percentage.Percentage;

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
