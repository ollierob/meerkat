package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public class ConvertibleBondDates extends MaturingBondDates {

    private final LocalDate finalConversion;

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
