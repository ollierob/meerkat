package net.meerkat.instrument;

import java.util.Collections;
import java.util.Map;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.utils.HasName;
import net.meerkat.identifier.instrument.HasInstrumentIds;

/**
 *
 * @author Ollie
 */
public interface InstrumentDefinition
        extends Instrument, HasInstrumentIds, HasName, Explainable {

    default String toShortString() {
        return this.instrumentIds().toString();
    }

    @Override
    default Map<String, Object> explain() {
        return Collections.emptyMap();
    }

    @CheckForNull
    <R> R handleWith(@Nonnull InstrumentDefinition.Handler<R> handler);

    interface Handler<R> {

        default R handle(final InstrumentDefinition security) {
            return security.handleWith(this);
        }

    }

}
