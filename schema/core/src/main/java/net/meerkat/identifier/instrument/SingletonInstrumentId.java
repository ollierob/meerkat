package net.meerkat.identifier.instrument;

import net.meerkat.identifier.SingletonId;

class SingletonInstrumentId
        extends SingletonId<InstrumentId>
        implements InstrumentIds {

    SingletonInstrumentId(final InstrumentId instrumentId) {
        super(instrumentId);
    }

}
