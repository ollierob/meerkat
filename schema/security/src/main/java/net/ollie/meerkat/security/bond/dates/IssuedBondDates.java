package net.ollie.meerkat.security.bond.dates;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public abstract class IssuedBondDates implements BondDates {

    @XmlAttribute(name = "issued", required = true)
    private LocalDate issued;

    @Deprecated
    protected IssuedBondDates() {
    }

    protected IssuedBondDates(final LocalDate issued) {
        this.issued = issued;
    }

    @Override
    public LocalDate issued() {
        return issued;
    }

}
