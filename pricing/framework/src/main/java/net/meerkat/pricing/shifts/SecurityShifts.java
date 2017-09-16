package net.meerkat.pricing.shifts;

import java.util.Optional;

import net.meerkat.Explainable;

/**
 *
 * @author ollie
 */
public interface SecurityShifts extends Explainable {

    default <S extends SecurityShifts> Optional<S> as(final Class<S> clazz) {
        return clazz.isAssignableFrom(this.getClass())
                ? Optional.of(clazz.cast(this))
                : Optional.empty();
    }

}
