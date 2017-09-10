package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public abstract class IssuedBondDates implements BondDates {

    private final LocalDate issueDate;

    protected IssuedBondDates(final LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public LocalDate issueDate() {
        return issueDate;
    }

}
