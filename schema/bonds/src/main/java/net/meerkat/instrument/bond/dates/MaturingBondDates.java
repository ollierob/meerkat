package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;
import java.util.Optional;

import net.meerkat.instrument.dates.Termed;

/**
 *
 * @author ollie
 */
public class MaturingBondDates extends IssuedBondDates implements Termed {

    private final LocalDate matures;

    public MaturingBondDates(final LocalDate issued, final LocalDate matures) {
        super(issued);
        this.matures = matures;
    }

    @Override
    public LocalDate maturityDate() {
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
