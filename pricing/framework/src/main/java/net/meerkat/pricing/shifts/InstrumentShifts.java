package net.meerkat.pricing.shifts;

import java.util.Map;
import java.util.Optional;

import net.meerkat.Explainable;

/**
 *
 * @author ollie
 */
public interface InstrumentShifts extends Explainable {

    default <S extends InstrumentShifts> Optional<S> as(final Class<S> clazz) {
        return clazz.isAssignableFrom(this.getClass())
                ? Optional.of(clazz.cast(this))
                : Optional.empty();
    }

    static InstrumentShifts none() {
        return new InstrumentShifts() {

            @Override
            public Map<String, Object> explain() {
                return this.explanationBuilder();
            }

        };
    }

}
