package net.ollie.meerkat.security.bond.dates;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.security.Termed;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class MaturingBondDates extends IssuedBondDates implements Termed {

    @XmlAttribute(name = "matures")
    private LocalDate matures;

    @Deprecated
    MaturingBondDates() {
    }

    public MaturingBondDates(final LocalDate issued, final LocalDate matures) {
        super(issued);
        this.matures = matures;
    }

    @Override
    public LocalDate matures() {
        return matures;
    }

    @Override
    public Optional<LocalDate> maturity() {
        return Optional.of(matures);
    }

    @Override
    public boolean isPerpetual() {
        return false;
    }

}
