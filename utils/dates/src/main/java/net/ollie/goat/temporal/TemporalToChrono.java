package net.ollie.goat.temporal;

import javax.annotation.Nonnull;
import java.time.Period;
import java.time.temporal.*;

/**
 * Bridge between {@link TemporalUnit} and {@link ChronoUnit}.
 *
 * @author Ollie
 */
public interface TemporalToChrono extends Temporal {

    @Nonnull
    Period period();

    default ChronoUnit toChronoUnit(final TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return (ChronoUnit) unit;
        }
        throw new UnsupportedTemporalTypeException("Unit is not a chrono unit: " + unit);
    }

    @Override
    @Deprecated
    default boolean isSupported(final TemporalUnit unit) {
        return this.isSupported(this.toChronoUnit(unit));
    }

    boolean isSupported(ChronoUnit unit);

    @Override
    @Deprecated
    default Temporal plus(final long amountToAdd, final TemporalUnit unit) {
        return this.plus(amountToAdd, this.toChronoUnit(unit));
    }

    Temporal plus(long amountToAdd, ChronoUnit unit) throws UnsupportedTemporalTypeException;

    @Override
    @Deprecated
    default long until(final Temporal endExclusive, final TemporalUnit unit) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    @Deprecated
    default boolean isSupported(final TemporalField field) {
        return field instanceof ChronoField
                && this.isSupported((ChronoField) field);
    }

    boolean isSupported(ChronoField field);

    @Override
    @Deprecated
    default long getLong(final TemporalField field) {
        if (field instanceof ChronoField) {
            return this.getLong((ChronoField) field);
        }
        throw new UnsupportedTemporalTypeException("Field is not a chrono field: " + field);
    }

    long getLong(ChronoField field) throws UnsupportedTemporalTypeException;

    @Override
    @Deprecated
    default Temporal with(final TemporalField field, final long newValue) {
        throw new UnsupportedOperationException(); //TODO
    }

}
