package net.meerkat.instrument;

import net.meerkat.identifier.instrument.InstrumentId;
import net.ollie.goat.data.CompositeProvider;

public interface InstrumentProvider<T, I extends Instrument> extends CompositeProvider<T, InstrumentId, I, InstrumentSnapshot<I>> {

}
