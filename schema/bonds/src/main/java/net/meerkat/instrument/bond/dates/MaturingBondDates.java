package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;

import net.meerkat.instrument.dates.Termed;

/**
 *
 * @author ollie
 */
public class MaturingBondDates extends IssuedBondDates implements Termed {

    private final LocalDate maturityDate;

    public MaturingBondDates(final LocalDate issueDate, final LocalDate maturityDate) {
        super(issueDate);
        this.maturityDate = maturityDate;
    }

    @Override
    public LocalDate maturityDate() {
        return maturityDate;
    }

    @Override
    public boolean isPerpetual() {
        return false;
    }

}
