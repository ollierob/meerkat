package net.meerkat.time.calendar.settlement;

import net.meerkat.temporal.date.DateWrapper;

import java.time.LocalDate;

public class UtcSettlementDate extends DateWrapper implements SettlementDate {

    protected UtcSettlementDate(final LocalDate date) {
        super(date);
    }

}
