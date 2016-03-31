package net.ollie.meerkat.security.bond.dates;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author ollie
 */
public class ConvertibleBondDates extends MaturingBondDates {

    @XmlAttribute(name = "final_conversion")
    private LocalDate finalConversion;

    @Deprecated
    ConvertibleBondDates() {
    }

    public ConvertibleBondDates(
            final LocalDate issued,
            final LocalDate finalConversion,
            final LocalDate matures) {
        super(issued, matures);
        this.finalConversion = finalConversion;
    }

    public LocalDate finalConversion() {
        return finalConversion;
    }

}
