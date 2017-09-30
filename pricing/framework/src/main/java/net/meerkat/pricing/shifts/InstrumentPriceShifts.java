package net.meerkat.pricing.shifts;

import java.util.Map;
import java.util.Optional;

import net.meerkat.Explainable;

/**
 *
 * @author ollie
 */
public interface InstrumentPriceShifts extends Explainable {

    default <S extends InstrumentPriceShifts> Optional<S> as(final Class<S> clazz) {
        return clazz.isAssignableFrom(this.getClass())
                ? Optional.of(clazz.cast(this))
                : Optional.empty();
    }

    static InstrumentPriceShifts none() {
        return new InstrumentPriceShifts() {

            @Override
            public Map<String, Object> explain() {
                return this.explanationBuilder();
            }

        };
    }

}
