package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import net.meerkat.time.calendar.Calendar;

/**
 *
 * @author ollie
 */
public interface SettlementCalendar extends Calendar {
    
    boolean isSettlement(LocalDate date) throws UnsupportedDateException;

}
