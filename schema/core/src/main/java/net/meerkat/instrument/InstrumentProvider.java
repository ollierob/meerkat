package net.meerkat.instrument;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.exception.InstrumentException;
import net.ollie.goat.data.CompositeProvider;

import javax.annotation.Nonnull;

public interface InstrumentProvider<T, I extends Instrument> extends CompositeProvider<T, InstrumentId, I, InstrumentSnapshot<I>> {

    @Nonnull
    default I require(final T temporal, final InstrumentIds ids) throws InstrumentException {
        throw new UnsupportedOperationException(); //TODO
    }

}
