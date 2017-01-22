package net.meerkat.security.bond.dates;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class PerpetualBondDates extends IssuedBondDates {

    @Deprecated
    PerpetualBondDates() {
    }

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
