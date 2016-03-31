package net.ollie.meerkat.security.bond.call;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.meerkat.numeric.Percentage;

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
