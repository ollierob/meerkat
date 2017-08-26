package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public abstract class IssuedBondDates implements BondDates {

    private final LocalDate issued;

    protected IssuedBondDates(final LocalDate issued) {
        this.issued = issued;
    }

    @Override
    public LocalDate issueDate() {
        return issued;
    }

}
