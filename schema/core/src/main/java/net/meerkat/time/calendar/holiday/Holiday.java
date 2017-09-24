package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;
import java.util.Objects;

import net.meerkat.utils.HasName;
import net.ollie.goat.temporal.date.DateWrapper;

/**
 *
 * @author Ollie
 */
public class Holiday extends DateWrapper implements HasName {

    private final String name;

    protected Holiday(final LocalDate date) {
        this("Unknown", date);
    }

    protected Holiday(final String name, final LocalDate date) {
        super(date);
        this.name = name;
    }

    protected Holiday named(final String name) {
        return Objects.equals(this.name, name)
                ? this
                : new Holiday(name, this.date());
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name + '@' + this.date();
    }

}
