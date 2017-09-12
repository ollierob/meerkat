package net.meerkat.instrument;

import net.meerkat.identifier.instrument.InstrumentId;
import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface InstrumentProvider<I extends Instrument> extends Provider<InstrumentId, I> {

    @Override
    default I require(final InstrumentId id) throws UnknownInstrumentException {
        return this.require(id, UnknownInstrumentException::new);
    }

}
