package net.ollie.meerkat.calculate.price.shifts;

import net.ollie.meerkat.calculate.price.SecurityPrice.InvalidShiftTypeException;
import net.ollie.meerkat.utils.Classes.Castable;

/**
 *
 * @author ollie
 */
public interface SecurityShifts extends Castable<SecurityShifts> {

    default <R extends SecurityShifts> R definiteCast(final Class<? extends R> clazz) throws InvalidShiftTypeException {
        return this.cast(clazz)
                .orElseThrow(() -> new InvalidShiftTypeException("Expected [" + clazz + "] but was [" + this.getClass() + "]!"));
    }

}
