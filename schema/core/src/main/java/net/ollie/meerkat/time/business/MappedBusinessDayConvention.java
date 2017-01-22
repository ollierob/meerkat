package net.ollie.meerkat.time.business;

import java.time.LocalDate;

import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
public class MappedBusinessDayConvention implements BusinessDayConvention {

    private final Map<LocalDate, LocalDate> days;

    public MappedBusinessDayConvention(final Map<LocalDate, LocalDate> days) {
        this.days = days;
    }

    @Override
    public LocalDate adjust(final LocalDate date) {
        return days.getOrDefault(date, date);
    }

}
