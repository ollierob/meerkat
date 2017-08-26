package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;
import java.util.Optional;

/**
 *
 * @author ollie
 */
public class PerpetualBondDates extends IssuedBondDates {

    public PerpetualBondDates(final LocalDate issued) {
        super(issued);
    }

    @Override
    public Optional<LocalDate> maturity() {
        return Optional.empty();
    }

    @Override
    public boolean isPerpetual() {
        return true;
    }

}
