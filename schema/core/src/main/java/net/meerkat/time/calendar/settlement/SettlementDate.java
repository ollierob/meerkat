package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import net.ollie.goat.temporal.date.DateWrapper;

/**
 *
 * @author Ollie
 */
public class SettlementDate extends DateWrapper {

    protected SettlementDate(final LocalDate date) {
        super(date);
    }

}
